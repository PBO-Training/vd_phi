import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import * as type from '../../../common/constant/type';
import { Listprops } from '../../../common/page-options';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { VM005002Service } from './vm005002.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DateConfig, parseDate } from '../../../common/datepicker-config/datepicker-config';
import { takeUntil } from 'rxjs/operators';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import * as XLSX from 'xlsx';
type AOA = any[][];

@Component({
  selector: 'app-vm005002',
  templateUrl: './vm005002.component.html',
  styleUrls: ['./vm005002.component.scss']
})
export class VM005002Component implements OnInit {
  Type: any = type;
  formSearch: FormGroup;
  data: AOA = [];
  updateData: any[] = [];
  updateDataOrigin: any[] = [];
  selectSystemSetting: any[] = [];
  selectHolidayDetail: any[] = [];
  selectShiftWork: any[] = [];
  isDataAvailable: boolean = false;
  isFormatWrong: boolean = false;
  direction: string;
  column: string;
  screenProps = new Listprops();
  isProcessData: boolean = false;
  isClear: boolean = false;
  isCollapsed = false;
  config = new DateConfig();
  wopts: XLSX.WritingOptions = { bookType: 'xlsx', type: 'array' };
  fileName: string = 'SheetJS.xlsx';
  stringViolation: string[];
  selectedViolation: any[];
  stringTimeLocale: string[];
  selectedTimeLocale: any[];
  authButton = new ScreenAction();

  constructor(
    private localStorage: StorageService,
    private vm005002Service: VM005002Service,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private router: Router,
    private route: ActivatedRoute,
    private datepipe: DatePipe,
    private commonFunctionService: CommonFunctionService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);  
    this.initDataLoad();
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {

  }

  initDataLoad() {
    const timeKeepingID = Number(this.route.snapshot.params.id);
    this.vm005002Service.vm005002InitList(timeKeepingID).subscribe(data => {
      if (data.error === null) {
        this.selectSystemSetting = data.content.listSystemSetting;
        this.selectHolidayDetail = data.content.listHolidayDetail;
        this.selectShiftWork = data.content.listShiftWork;
        this.isDataAvailable = true;
        this.overideSetting();
      }
      else {
        //this.openPopupIsDataAvailable('notification.500');
        this.router.navigate(['/403']);
      }
    });
  }

  processDataTimeKeeping() {
    this.vm005002Service.vm005002processTimeKeeping(this.updateDataOrigin).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
      if (datacheck.error === null) {
        this.updateData = datacheck.content.data;
        this.updateDataOrigin = datacheck.content.data;
        this.isProcessData = true;
        this.screenProps.totalRecord = this.updateData.length;
        this.resetDataList();
      }
    });
  }

  saveDataTimeKeeping() {
    this.updateDataOrigin = this.updateDataOrigin.filter(
      item => item.violation != "OK" && item.employeeName != '002');
    const timeKeepingID = Number(this.route.snapshot.params.id);
    this.vm005002Service.vm005002saveTimeKeeping(this.updateDataOrigin, timeKeepingID).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
      if (datacheck.error === null) {
        this.router.navigate(['/timekeeping']);
      }
    });
  }

  changePageSize(pageSize: number): void {
    this.screenProps.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenProps.pageSize);
  }

  pageChangeOutput(currentPage: number) {
    this.screenProps.pageNum = currentPage;
  }

  convertUTCDateToLocalDate(date) {
    try {
      var newDate = new Date(date.getTime() + date.getTimezoneOffset() * 60 * 1000);
      var offset = date.getTimezoneOffset() / 60;
      var hours = date.getHours();
      newDate.setHours(hours - offset);
    }
    catch (e) {
      this.isFormatWrong = true;
    }
    return newDate;
  }

  onFileChange(evt: any) {
    this.resetSort();
    const timeKeepingID = Number(this.route.snapshot.params.id);
    /* wire up file reader */
    const target: DataTransfer = <DataTransfer>(evt.target);
    if (target.files.length !== 1) throw new Error('Cannot use multiple files');
    const reader: FileReader = new FileReader();
    reader.onload = (e: any) => {
      /* read workbook */
      const bstr: string = e.target.result;
      const wb: XLSX.WorkBook = XLSX.read(bstr, {
        type: 'binary',
        cellDates: true,
        cellNF: false,
        cellText: false
      });
      /* grab first sheet */
      const wsname: string = wb.SheetNames[0];
      const ws: XLSX.WorkSheet = wb.Sheets[wsname];

      /* save data */
      this.data = <AOA>(XLSX.utils.sheet_to_json(ws, { header: 1 }));
      let header = 0;

      for (let item of this.data) {
        if (header > 0) {
          let indexCheckInAM = 0;
          let indexCheckOutAM = 0;
          let indexCheckInPM = 0;
          let indexCheckOutPM = 0;

          /* Default value for check In/Out - AM */
          let isInAMOK = 0;
          let isInAMMissing = 0;
          let isOutAMOk = 0;
          let isOutAMMissing = 0;
          let indexAMIn = 0;
          let indexAMOut = 0;
          /* Default value for check In/Out - PM */
          let isInPMOK = 0;
          let isInPMMissing = 0;
          let isOutPMOk = 0;
          let isOutPMMissing = 0;
          let indexPMIn = 0;
          let indexPMOut = 0;

          let employeeCode = item[0];

          let getDate = new Date(this.convertUTCDateToLocalDate(item[2]));
          let getStartTimeAM = new Date(this.convertUTCDateToLocalDate(item[2]));
          getStartTimeAM.setHours(type.TimeDefault.startHourAM);
          getStartTimeAM.setMinutes(type.TimeDefault.startMinAM);
          getStartTimeAM.setSeconds(0);

          let getEndTimeAM = new Date(this.convertUTCDateToLocalDate(item[2]));
          getEndTimeAM.setHours(type.TimeDefault.endHourAM);
          getEndTimeAM.setMinutes(type.TimeDefault.endMinAM);
          getEndTimeAM.setSeconds(0);

          let getStartTimePM = new Date(this.convertUTCDateToLocalDate(item[2]));
          getStartTimePM.setHours(type.TimeDefault.startHourPM);
          getStartTimePM.setMinutes(type.TimeDefault.startMinPM);
          getStartTimePM.setSeconds(0);

          let getEndTimePM = new Date(this.convertUTCDateToLocalDate(item[2]));
          getEndTimePM.setHours(type.TimeDefault.endHourPM);
          getEndTimePM.setMinutes(type.TimeDefault.endMinPM);
          getEndTimePM.setSeconds(0);

          // For AM         
          // Loop all record to check data checkin is OK - AM
          for (let checkInAM of item) {
            let compareDate = new Date(checkInAM);
            compareDate.setDate(getStartTimeAM.getDate());
            compareDate.setMonth(getStartTimeAM.getMonth());
            compareDate.setFullYear(getStartTimeAM.getFullYear());

            if (indexCheckInAM > 2) {
              let calStartTimeAM = getStartTimeAM;
              if (this.isShiftWork(compareDate, employeeCode, "check-in-am") != null) {
                calStartTimeAM = this.isShiftWork(compareDate, employeeCode, "check-in-am");
              }

              var hoursBefore = new Date(calStartTimeAM);
              hoursBefore.setHours(hoursBefore.getHours() + 1);

              if (compareDate.getHours() < 12) {
                isInAMMissing++;
                if (this.isCheckInOKAM(compareDate, calStartTimeAM) && isInAMOK === 0) {
                  if (compareDate > hoursBefore && indexAMIn === 0) {
                    isInAMMissing = 0;
                  }
                  else {
                    if (indexAMIn === 0) {
                      indexAMIn = indexCheckInAM;
                    }
                  }
                }
                else {
                  indexAMIn = 0;
                  isInAMOK++;
                }
              }
              else {
                if (isInAMMissing === 0) {
                  isInAMMissing = 0;
                }
              }
            }
            indexCheckInAM++;
          }
          // For AM
          // Loop all record to check data checkout is OK - AM
          for (let checkOutAM of item) {
            let compareDate = new Date(checkOutAM);
            compareDate.setDate(getStartTimeAM.getDate());
            compareDate.setMonth(getStartTimeAM.getMonth());
            compareDate.setFullYear(getStartTimeAM.getFullYear());

            if (indexCheckOutAM > 2) {
              let calStartTimeAM = getStartTimeAM;
              let calEndTimeAM = getEndTimeAM;
              let calStartTimePM = getStartTimePM;

              if (this.isShiftWork(compareDate, employeeCode, "check-in-am") != null) {
                calStartTimeAM = this.isShiftWork(compareDate, employeeCode, "check-in-am");
              }
              if (this.isShiftWork(compareDate, employeeCode, "check-out-am") != null) {
                calEndTimeAM = this.isShiftWork(compareDate, employeeCode, "check-out-am");
              }
              if (this.isShiftWork(compareDate, employeeCode, "check-in-pm") != null) {
                calStartTimePM = this.isShiftWork(compareDate, employeeCode, "check-in-pm");
              }

              var hoursBefore = new Date(calStartTimeAM);
              hoursBefore.setHours(hoursBefore.getHours() + 1);

              if (compareDate.getHours() < 12) {
                isOutAMMissing++;
                if (this.isCheckOutOKAM(compareDate, calEndTimeAM, calStartTimePM) && isOutAMOk === 0) {
                  if (compareDate < hoursBefore && indexAMOut === 0) {
                    isOutAMMissing = 0;
                  }
                  else {
                    indexAMOut = indexCheckOutAM;
                  }
                }
                else {
                  indexAMOut = 0;
                  isOutAMOk++;
                }
              }
              else {
                if (isOutAMMissing === 0) {
                  isOutAMMissing = 0;
                }
              }
            }
            indexCheckOutAM++;
          }

          // For PM
          // Loop all record to check data checkin is OK - PM
          for (let checkInPM of item) {
            let compareDate = new Date(checkInPM);
            compareDate.setDate(getStartTimePM.getDate());
            compareDate.setMonth(getStartTimePM.getMonth());
            compareDate.setFullYear(getStartTimePM.getFullYear());

            if (indexCheckInPM > 2) {
              let calEndTimeAM = getEndTimeAM;
              let calStartTimePM = getStartTimePM;
              if (this.isShiftWork(compareDate, employeeCode, "check-out-am") != null) {
                calEndTimeAM = this.isShiftWork(compareDate, employeeCode, "check-out-am");
              }
              if (this.isShiftWork(compareDate, employeeCode, "check-in-pm") != null) {
                calStartTimePM = this.isShiftWork(compareDate, employeeCode, "check-in-pm");
              }

              var hoursBefore = new Date(calStartTimePM);
              hoursBefore.setHours(hoursBefore.getHours() + 1);

              if (compareDate.getHours() >= 12) {
                isInPMMissing++;
                if (this.isCheckInOKPM(compareDate, calEndTimeAM, calStartTimePM) && isInPMOK === 0) {
                  if (compareDate > hoursBefore && indexPMIn === 0) {
                    isInPMMissing = 0;
                  }
                  else {
                    if (indexPMIn === 0) {
                      indexPMIn = indexCheckInPM;
                    }
                  }
                }
                else {
                  indexPMIn = 0;
                  isInPMOK++;
                }
              }
              else {
                if (isInPMMissing === 0) {
                  isInPMMissing = 0;
                }
              }
            }
            indexCheckInPM++;
          }
          // For PM
          // Loop all record to check data checkout is OK - PM
          for (let checkOutPM of item) {
            let compareDate = new Date(checkOutPM);
            compareDate.setDate(getStartTimePM.getDate());
            compareDate.setMonth(getStartTimePM.getMonth());
            compareDate.setFullYear(getStartTimePM.getFullYear());

            if (indexCheckOutPM > 2) {
              let calStartTimePM = getStartTimePM;
              let calEndTimePM = getEndTimePM;
              if (this.isShiftWork(compareDate, employeeCode, "check-out-pm") != null) {
                calEndTimePM = this.isShiftWork(compareDate, employeeCode, "check-out-pm");
              }

              var hoursBefore = new Date(calStartTimePM);
              hoursBefore.setHours(hoursBefore.getHours() + 1);

              if (compareDate.getHours() >= 12) {
                isOutPMMissing++;
                if (this.isCheckOutOKPM(compareDate, calEndTimePM) && isOutPMOk === 0) {
                  //indexPMOut = indexCheckOutPM;
                  if (compareDate < hoursBefore && indexPMOut === 0) {
                    isOutPMMissing = 0;
                  }
                  else {
                    indexPMOut = indexCheckOutPM;
                  }
                }
                else {
                  indexPMOut = 0;
                  isOutPMOk++;
                }
              }
              else {
                if (isOutPMMissing === 0) {
                  isOutPMMissing = 0;
                }
              }
            }
            indexCheckOutPM++;
          }
          if (!this.isFormatWrong) {
            // Show error Check In / Out AM
            if (isInAMOK === 0 && isInAMMissing != 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkInAM;
              let checkInAM = this.datepipe.transform(item[indexAMIn], "HH:mm");
              let checkOutAM = null;
              let checkInPM = null;
              let checkOutPM = null;
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimeAM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimeAM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              indexAMIn = 0;
            }
            if (isInAMMissing === 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkInAMMissing;
              let checkInAM = type.TimeDefault.startHourAM + ":" + type.TimeDefault.startMinAM;
              let checkOutAM = null;
              let checkInPM = null;
              let checkOutPM = null;
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimeAM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimeAM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              isInAMMissing = 0;
            }
            if (isOutAMOk === 0 && isOutAMMissing != 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkOutAM;
              let checkInAM = null;
              let checkOutAM = this.datepipe.transform(item[indexAMOut], "HH:mm");
              let checkInPM = null;
              let checkOutPM = null;
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimeAM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimeAM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              indexAMOut = 0;
            }
            if (isOutAMMissing === 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkOutAMMissing;
              let checkInAM = null;
              let checkOutAM = type.TimeDefault.endHourAM + ":" + type.TimeDefault.endMinAM;
              let checkInPM = null;
              let checkOutPM = null;
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimeAM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimeAM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              isOutAMMissing = 0;
            }

            // Show error Check In / Out PM
            if (isInPMOK === 0 && isInPMMissing != 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkInPM;
              let checkInAM = null;
              let checkOutAM = null;
              let checkInPM = this.datepipe.transform(item[indexPMIn], "HH:mm");
              let checkOutPM = null;
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimePM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimePM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              indexPMIn = 0;
            }
            if (isInPMMissing === 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkInPMMissing;
              let checkInAM = null;
              let checkOutAM = null;
              let checkInPM = type.TimeDefault.startHourPM + ":" + type.TimeDefault.startMinPM;
              let checkOutPM = null;
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimePM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimePM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              isInPMMissing = 0;
            }
            if (isOutPMOk === 0 && isOutPMMissing != 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkOutPM;
              let checkInAM = null;
              let checkOutAM = null;
              let checkInPM = null;
              let checkOutPM = this.datepipe.transform(item[indexPMOut], "HH:mm");
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimePM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimePM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              indexPMOut = 0;
            }
            if (isOutPMMissing === 0 && !this.isHoliday(getDate)) {
              let employeeCode = item[0] + "";
              let employeeName = item[1] + "";
              let date = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(item[2]), 'yyyy-MM-dd'));
              let actViolate = type.ViolationType.checkOutPMMissing;
              let checkInAM = null;
              let checkOutAM = null;
              let checkInPM = null;
              let checkOutPM = type.TimeDefault.endHourPM + ":" + type.TimeDefault.endMinPM;
              let times = 0;
              let minusHour = 0;
              let startDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getStartTimePM), 'yyyy-MM-dd'));
              let endDate = new Date(this.datepipe.transform(this.convertUTCDateToLocalDate(getEndTimePM), 'yyyy-MM-dd'));
              let timeKeeping = new type.TimeKeeping(employeeCode, employeeName, date, actViolate,
                checkInAM, checkOutAM, checkInPM, checkOutPM,
                times, minusHour, timeKeepingID, startDate, endDate, false);
              this.updateData.push(timeKeeping);
              isOutPMMissing = 0;
            }
          }
          else {
            this.updateData = [];
          }
        }
        header++;
      }
      this.screenProps.pageNum = 1;
      this.screenProps.page = 1;
      this.screenProps.pageSize = this.localStorage.getPageSize() !== null ? Number(this.localStorage.getPageSize()) : 30;
      this.screenProps.totalRecord = this.updateData.length;
      this.screenProps.loading = false;
    };
    this.isFormatWrong = false;
    this.updateDataOrigin = this.updateData;
    this.isClear = true;
    reader.readAsBinaryString(target.files[0]);
  }

  openPopupIsDataAvailable(key: string) {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components
    // https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalNotificationMaintenanceComponent, { backdrop: 'static', keyboard: false });
    modalRef.componentInstance.key = key;
    modalRef.result.then(
      (result) => {
        if (result === 'reload') {
          location.reload();
        } else {
          this.router.navigate(['/dashboard']);
        }
      },
      (reason) => {
      });
  }

  overideSetting() {
    //Overide start time AM
    let indexStartHourAM = this.selectSystemSetting[0].timeStartWorkAM.indexOf(":");
    let splittedStartHourAM = parseInt(this.selectSystemSetting[0].timeStartWorkAM.slice(0, indexStartHourAM));
    let splittedStartMinAM = parseInt(this.selectSystemSetting[0].timeStartWorkAM.slice(indexStartHourAM + 1));
    type.TimeDefault.startHourAM = splittedStartHourAM;
    type.TimeDefault.startMinAM = splittedStartMinAM;

    //Overide end time AM
    let indexEndHourAM = this.selectSystemSetting[0].timeEndWorkAM.indexOf(":");
    let splittedEndHourAM = parseInt(this.selectSystemSetting[0].timeEndWorkAM.slice(0, indexEndHourAM));
    let splittedEndMinAM = parseInt(this.selectSystemSetting[0].timeEndWorkAM.slice(indexEndHourAM + 1));
    type.TimeDefault.endHourAM = splittedEndHourAM;
    type.TimeDefault.endMinAM = splittedEndMinAM;

    //Overide start time PM
    let indexStartHourPM = this.selectSystemSetting[0].timeStartWorkPM.indexOf(":");
    let splittedStartHourPM = parseInt(this.selectSystemSetting[0].timeStartWorkPM.slice(0, indexStartHourPM));
    let splittedStartMinPM = parseInt(this.selectSystemSetting[0].timeStartWorkPM.slice(indexStartHourPM + 1));
    type.TimeDefault.startHourPM = splittedStartHourPM;
    type.TimeDefault.startMinPM = splittedStartMinPM;

    //Overide end time PM
    let indexEndHourPM = this.selectSystemSetting[0].timeEndWorkPM.indexOf(":");
    let splittedEndHourPM = parseInt(this.selectSystemSetting[0].timeEndWorkPM.slice(0, indexEndHourPM));
    let splittedEndMinPM = parseInt(this.selectSystemSetting[0].timeEndWorkPM.slice(indexEndHourPM + 1));
    type.TimeDefault.endHourPM = splittedEndHourPM;
    type.TimeDefault.endMinPM = splittedEndMinPM;

    //Overide last weekend
    let splitted = this.selectSystemSetting[0].lastWeekend.split(";");
    type.TimeDefault.lastWeekend = splitted;
  }

  isHoliday(date: Date) {
    // Check last weekend
    for (let o of type.TimeDefault.lastWeekend.toString()) {
      if (date) {
        if (date.getDay() == Number(o)) {
          return true;
        }
      }
    }
    // Check holiday
    for (let p of this.selectHolidayDetail) {
      if (date) {
        let getDate = new Date(p.valueResponse);
        if (date.getDate() == getDate.getDate() && date.getMonth() == getDate.getMonth() && date.getFullYear() == getDate.getFullYear()) {
          return true;
        }
      }
    }
    return false;
  }

  ConvertUTCTimeToLocalTime(UTCDateString) {
    var convertdLocalTime = new Date(UTCDateString);
    var hourOffset = convertdLocalTime.getTimezoneOffset() / 60;
    convertdLocalTime.setHours(convertdLocalTime.getHours() + hourOffset);
    return convertdLocalTime;
  }

  isShiftWork(date: Date, employeeCode: String, checkInOut: String) {
    // Check shiftwork
    for (let p of this.selectShiftWork) {
      if (date) {
        let getStartDateAM = this.ConvertUTCTimeToLocalTime(p.shiftWorkStartDateAM);
        let getEndDateAM = this.ConvertUTCTimeToLocalTime(p.shiftWorkEndDateAM);
        let getStartDatePM = this.ConvertUTCTimeToLocalTime(p.shiftWorkStartDatePM);
        let getEndDatePM = this.ConvertUTCTimeToLocalTime(p.shiftWorkEndDatePM);

        let getDay = date.getDate();
        let getMonth = date.getMonth();
        let getYear = date.getFullYear();        

        if (employeeCode.toString() === p.employeeCode) {
          if (date.getDate() >= getStartDateAM.getDate() && date.getMonth() >= getStartDateAM.getMonth() && date.getFullYear() >= getStartDateAM.getFullYear()
            && date.getDate() <= getEndDatePM.getDate() && date.getMonth() <= getEndDatePM.getMonth() && date.getFullYear() <= getEndDatePM.getFullYear()) {
            if (checkInOut === "check-in-am") {
              getStartDateAM.setDate(getDay);
              getStartDateAM.setMonth(getMonth);
              getStartDateAM.setFullYear(getYear);
              return getStartDateAM;
            }
            else if (checkInOut === "check-out-am") {
              getEndDateAM.setDate(getDay);
              getEndDateAM.setMonth(getMonth);
              getEndDateAM.setFullYear(getYear);
              return getEndDateAM;
            }
            else if (checkInOut === "check-in-pm") {
              getStartDatePM.setDate(getDay);
              getStartDatePM.setMonth(getMonth);
              getStartDatePM.setFullYear(getYear);
              return getStartDatePM;
            }
            else if (checkInOut === "check-out-pm") {
              getEndDatePM.setDate(getDay);
              getEndDatePM.setMonth(getMonth);
              getEndDatePM.setFullYear(getYear);
              return getEndDatePM;
            }
          }
        }
      }
    }
    return null;
  }

  isCheckInOKAM(date: Date, startTimeAM: Date) {
    if (date > startTimeAM) {
      return true;
    }
    return false;
  }

  isCheckInOKPM(date: Date, endTimeAM: Date, startTimePM: Date) {
    if (date > endTimeAM && date <= startTimePM) {
      return false;
    }
    return true;
  }

  isCheckOutOKAM(date: Date, endTimeAM: Date, startTimePM: Date) {
    if (date >= endTimeAM && date <= startTimePM) {
      return false;
    }
    return true;
  }

  isCheckOutOKPM(date: Date, endTimePM: Date) {
    if (date >= endTimePM) {
      return false;
    }
    return true;
  }

  resetSort() {
    // reset sort
    this.column = 'date';
    this.direction = 'asc';
  }

  onSort({ column, direction }: SortEvent) {
    this.direction = direction;
    this.column = column;
    this.updateData = [...this.updateData].sort((a, b) => {
      let res: number;
      switch (column) {
        case 'employeeCode':
          res = compare(a.employeeCode, b.employeeCode);
          return direction === 'asc' ? res : -res;
        case 'employeeName':
          res = compare(a.employeeName, b.employeeName);
          return direction === 'asc' ? res : -res;
        case 'date':
          res = compare(a.date, b.date);
          return direction === 'asc' ? res : -res;
        case 'violation':
          res = compare(a.violation, b.violation);
          return direction === 'asc' ? res : -res;
      }
    });
  }

  resetForm() {
    this.updateData = [];
    this.updateDataOrigin = [];
    this.stringViolation = [];
    this.stringTimeLocale = [];
    this.isProcessData = false;
    this.isClear = false;
  }

  back(event: any) {
    this.router.navigate(['/timekeeping']);
  }

  searchDataList() {
    //EmployeeCode
    let employeeCode = ((document.getElementById("employeeCodeFilter") as HTMLInputElement).value);
    //EmployeeName
    let employeeName = ((document.getElementById("employeeNameFilter") as HTMLInputElement).value);
    //Start Date
    let start = ((document.getElementById("startDateFilter") as HTMLInputElement).value);    
    let startDate = new Date(parseDate(start));  
    startDate.setHours(0);   
    startDate.setMinutes(0);   
    startDate.setMilliseconds(0);    
    //End Date
    let end = ((document.getElementById("endDateFilter") as HTMLInputElement).value);
    let endDate = new Date(parseDate(end)); 
    endDate.setHours(23);   
    endDate.setMinutes(59);   
    endDate.setMilliseconds(59);   
    //Violation
    let violation = [];
    if (this.stringViolation) {
      this.stringViolation.forEach(item => {
        if (item == "1") {
          violation.push("check-in");
        }
        else if (item == "2") {
          violation.push("check-out");
        }
        else if (item == "3") {
          violation.push("missing-check-in");
        }
        else if (item == "4") {
          violation.push("missing-check-out");
        }
      });
    }
    //TimeLocale
    let timelocale = [];
    if (this.stringTimeLocale) {
      this.stringTimeLocale.forEach(item => {
        if (item == "5") {
          timelocale.push("-am");
        }
        else if (item == "6") {
          timelocale.push("-pm");
        }
      });
    }    
    //Filter
    if (start && !end) {
      this.updateData = this.updateDataOrigin.filter(a => a.employeeCode.toLowerCase()
        .indexOf(employeeCode.toLowerCase()) !== -1 && a.employeeName.toLowerCase()
          .indexOf(employeeName.toLowerCase()) !== -1 && new Date(a.date) >= startDate);
    }
    else if (!start && end) {
      this.updateData = this.updateDataOrigin.filter(a => a.employeeCode.toLowerCase()
        .indexOf(employeeCode.toLowerCase()) !== -1 && a.employeeName.toLowerCase()
          .indexOf(employeeName.toLowerCase()) !== -1 && new Date(a.date) <= endDate);
    }
    else if (start && end) {
      this.updateData = this.updateDataOrigin.filter(a => a.employeeCode.toLowerCase()
        .indexOf(employeeCode.toLowerCase()) !== -1 && a.employeeName.toLowerCase()
          .indexOf(employeeName.toLowerCase()) !== -1 && new Date(a.date) >= startDate && new Date(a.date) <= endDate);
    }
    else {
      this.updateData = this.updateDataOrigin.filter(a => a.employeeCode.toLowerCase()
        .indexOf(employeeCode.toLowerCase()) !== -1 && a.employeeName.toLowerCase()
          .indexOf(employeeName.toLowerCase()) !== -1);
    }
    if (violation.length != 0) {
      this.updateData = this.updateData.filter(item => {
        let replaceString = item.violation.replace("-am", "").replace("-pm", "");
        for (let key in violation) {
          if (violation[key] === replaceString)
            return true;
        }
        return false;
      });
    }
    if (timelocale.length != 0) {
      this.updateData = this.updateData.filter(item => {
        let replaceString = item.violation;
        for (let key in timelocale) {
          if (replaceString.includes(timelocale[key]))
            return true;
        }
        return false;
      });
    }
    this.screenProps.totalRecord = this.updateData.length;
  }

  resetDataList() {        
    //EmployeeCode
    (document.getElementById("employeeCodeFilter") as HTMLInputElement).value = "";
    //EmployeeName
    (document.getElementById("employeeNameFilter") as HTMLInputElement).value = "";
    //Start Date
    let start = <HTMLInputElement> document.getElementById('startDateFilter') ;    
    start.value = null;              
    //End Date
    let end = <HTMLInputElement> document.getElementById('endDateFilter') ;    
    end.value = null;  
    //Violation           
    this.selectedViolation = [];
    this.stringViolation = [];
    //TimeLocale           
    this.selectedTimeLocale = [];
    this.stringTimeLocale = [];
    this.updateData = this.updateDataOrigin;
    this.screenProps.totalRecord = this.updateData.length;
  }

  changeViolation(event: any) {
    this.stringViolation = event
  }

  changeTimeLocale(event: any) {
    this.stringTimeLocale = event
  }
}
