import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import * as type from '../../../common/constant/type';
import { DateConfig } from '../../../common/datepicker-config/datepicker-config';
import { Listprops } from '../../../common/page-options';
import { DateValidator } from '../../../common/validator/date-validator';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import { TimeKeeping } from './vm005003-entity';
import { VM005003Service } from './vm005003.service';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { TranslateService } from '@ngx-translate/core';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-vm005003',
  templateUrl: './vm005003.component.html',
  styleUrls: ['./vm005003.component.scss']
})
export class VM005003Component implements OnInit {

  checked: boolean;
  indeterminate = false;

  displayMessage: string;
  errorsCode: string;
  direction: string;
  column: string;
  submitted: boolean;
  isCollapsed = false;
  formSearch: FormGroup;
  formBaseSearch: FormGroup;
  timeKeepingSearchList: TimeKeeping[];

  screenPropsSearch = new Listprops();
  config = new DateConfig();

  Type: any = type;

  valueOldForSearch = {
    timeKeepingID: [null],
    employeeCode: null,
    employeeName: null,
    startDate: [null],
    endDate: [null],
    violation: [],
    timeLocale: [],
  };
  authButton = new ScreenAction();

  constructor(
    private formBuilder: FormBuilder,
    private vm005003Service: VM005003Service,
    private localStorage: StorageService,
    public translationService: TranslationService,
    private modalService: NgbModal,
    private router: Router,
    public toastService: ToastService,
    private route: ActivatedRoute,
    private translate: TranslateService,
    private commonFunctionService: CommonFunctionService,
    private titleService: Title,
  ) {
    this.readstorageData();
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.initForm();
    this.formBaseSearch = this.formBuilder.group({
      employeeCode: null,
      employeeName: null,
      timeKeepingID: Number(this.route.snapshot.params.id),
      startDate: null,
      violation: [],
      timeLocale: [],
      endDate: null,
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    });

    this.formSearch.patchValue({
      employeeCode: null,
      employeeName: null,
      startDate: null,
      endDate: null,
      timeKeepingID: Number(this.route.snapshot.params.id),
      violation: [],
      timeLocale: [],
    });
    this.initDataSearch(null, this.localStorage.getPageSize(), this.formBaseSearch);
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.detail-timekeeping').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.detail-timekeeping'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

  }

  readstorageData() {
    const storage: any = this.localStorage.getUser();
  }

  initForm(): void {
    this.formSearch = this.formBuilder.group({
      employeeCode: null,
      employeeName: null,
      startDate: null,
      endDate: null,
      violation: [],
      timeLocale: [],
      timeKeepingID: Number(this.route.snapshot.params.id),
      pageNum: [1],
      pageSize: [this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30],
    },
      {
        validator: Validators.compose([DateValidator.dateLessThan('startDate', 'endDate')])
      });
  }

  resetForm() {
    this.localStorage.removeSearchData();
    this.initForm();
    this.formBaseSearch.patchValue({
      employeeCode: null,
      employeeName: null,
      startDate: null,
      endDate: null,
      violation: [],
      timeLocale: [],
      timeKeepingID: Number(this.route.snapshot.params.id),
    });
    this.formSearch.patchValue({
      employeeCode: null,
      employeeName: null,
      startDate: null,
      endDate: null,
      timeKeepingID: Number(this.route.snapshot.params.id),
      violation: [],
      timeLocale: [],
    });
    this.screenPropsSearch.page = 1;
    this.formBaseSearch.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;
    this.initDataSearch(null, this.localStorage.getPageSize(), this.formBaseSearch);
  }

  parseDateToMonth(dateStr) {
    let checkDate = typeof dateStr;
    if (checkDate.toString() == 'string') {
      var parts = dateStr.split("-")
      return new Date(parts[2], parts[1] - 1, parts[0])
    }
    return dateStr;
  }

  setDefaultTime(date): String {
    let checkDate = new Date(date);
    if (checkDate.toString() != 'Invalid Date' && date) {
      let isoDate = new Date(date.getTime() - date.getTimezoneOffset() * 60000).toISOString();
      return isoDate;
    }
    return date;
  }

  searchSendList() {
    this.unCheckAll();
    this.screenPropsSearch.loading = true;
    this.screenPropsSearch.page = 1;

    //this.formBaseSend.value.filterType = type.Type.typeSend;
    this.formBaseSearch.value.pageNum = 1;
    this.formBaseSearch.value.pageSize = this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30;

    this.valueOldForSearch = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseSearch.value.timeKeepingID = this.valueOldForSearch.timeKeepingID;
    this.formBaseSearch.value.employeeName = this.valueOldForSearch.employeeName;
    this.formBaseSearch.value.employeeCode = this.valueOldForSearch.employeeCode;
    this.formBaseSearch.value.startDate = this.valueOldForSearch.startDate;
    this.formBaseSearch.value.endDate = this.valueOldForSearch.endDate;
    this.formBaseSearch.value.violation = this.valueOldForSearch.violation;
    this.formBaseSearch.value.timeLocale = this.valueOldForSearch.timeLocale;

    let startDate = this.parseDateToMonth(this.valueOldForSearch.startDate);
    if (startDate) {
      startDate.setHours(type.TimeDefault.startHourAM);
      startDate.setMinutes(type.TimeDefault.startMinAM);
    }
    let endDate = this.parseDateToMonth(this.valueOldForSearch.endDate);
    if (endDate) {
      endDate.setHours(type.TimeDefault.endHourPM);
      endDate.setMinutes(type.TimeDefault.endMinPM);
    }
    this.formBaseSearch.value.startDate = this.setDefaultTime(startDate);
    this.formBaseSearch.value.endDate = this.setDefaultTime(endDate);

    this.initDataSearch(null, this.localStorage.getPageSize() !== null ? this.localStorage.getPageSize() : 30, this.formBaseSearch);
    this.localStorage.saveSearchData(this.formBaseSearch.value, null);
  }

  initDataSearch(pageN: any, pSize: any, form: FormGroup) {
    this.resetSort();
    this.submitted = true;
    const formData = {
      pageSize: pSize,
      pageNum: pageN,
      ...form.value,
    };
    if (this.formSearch.valid) {
      this.submitted = false;
      this.vm005003Service.vm005003searchTimeKeepingDetail(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.timeKeepingSearchList = datacheck.content.data;

          this.screenPropsSearch.pageNum = datacheck.content.pageNum ? datacheck.content.pageNum - 1 : 0;
          this.screenPropsSearch.pageSize = datacheck.content.pageSize ? datacheck.content.pageSize : 30;
          this.screenPropsSearch.totalRecord = datacheck.content.totalElements;
          this.screenPropsSearch.loading = false;
          this.timeKeepingSearchList = datacheck.content.data;
        }
      }, err => {
        const httpErr = err.error;
        this.timeKeepingSearchList = [];
        if (!httpErr) {
          return;
        }
        else {
          this.displayErrorMessage(httpErr);
        }
      });
    }
  }

  displayErrorMessage(errors) {
    const formControl = this.formSearch.get(errors.error.itemName);
    if (formControl) {
      this.translationService.getTranslation(errors.error.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = errors.error.code;
        this.displayMessage = errorMessage;
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }

  changePageSize(pageSize: number): void {
    this.screenPropsSearch.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenPropsSearch.pageSize);
    this.formBaseSearch.value.pageSize = pageSize;
    this.unCheckAll();
    this.initDataSearch(null, this.localStorage.getPageSize(), this.formBaseSearch);
  }

  onSort({ column, direction }: SortEvent) {
    this.direction = direction;
    this.column = column;
    this.timeKeepingSearchList = [...this.timeKeepingSearchList].sort((a, b) => {
      let res: number;
      switch (column) {
        case 'date':
          res = compare(a.date, b.date);
          return direction === 'asc' ? res : -res;
        case 'employeeCode':
          res = compare(a.employeeCode, b.employeeCode);
          return direction === 'asc' ? res : -res;
        case 'employeeName':
          res = compare(a.employeeName, b.employeeName);
          return direction === 'asc' ? res : -res;
        case 'violation':
          res = compare(a.violation, b.violation);
          return direction === 'asc' ? res : -res;
      }
    });
  }

  pageChangeOutput(currentPage: number) {
    this.formBaseSearch.value.pageNum = currentPage;
    this.screenPropsSearch.pageNum = currentPage - 1;
    this.formBaseSearch.value.pageSize = this.screenPropsSearch.pageSize;
    this.unCheckAll();
    this.initDataSearch(null, this.localStorage.getPageSize(), this.formBaseSearch);
  }

  resetSort() {
    // reset sort
    this.column = 'date';
    this.direction = 'asc';
  }

  onAddNewViolation(event) {
    const itemValue: number = Number(event);
    this.valueOldForSearch = JSON.parse(JSON.stringify(this.formSearch.value));
    let newViolationIDList: number[] = this.valueOldForSearch.violation;
    if (newViolationIDList.length > 1) {
      if (itemValue === -1) {
        newViolationIDList = [-1];
      } else {
        if (newViolationIDList.includes(-1)) {
          newViolationIDList = newViolationIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newViolationIDList = [0];
      } else {
        if (newViolationIDList.includes(0)) {
          newViolationIDList = newViolationIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      violation: newViolationIDList,
    });
  }

  onAddNewTimeLocale(event) {
    const itemValue: number = Number(event);
    this.valueOldForSearch = JSON.parse(JSON.stringify(this.formSearch.value));
    let newTimeLocaleIDList: number[] = this.valueOldForSearch.timeLocale;
    if (newTimeLocaleIDList.length > 1) {
      if (itemValue === -1) {
        newTimeLocaleIDList = [-1];
      } else {
        if (newTimeLocaleIDList.includes(-1)) {
          newTimeLocaleIDList = newTimeLocaleIDList.filter(item => item !== -1);
        }
      }
      if (itemValue === 0) {
        newTimeLocaleIDList = [0];
      } else {
        if (newTimeLocaleIDList.includes(0)) {
          newTimeLocaleIDList = newTimeLocaleIDList.filter(item => item !== 0);
        }
      }
    }
    this.formSearch.patchValue({
      timeLocale: newTimeLocaleIDList,
    });
  }

  onItemChecked(id: number, event) {
    this.updateCheckedSet(id, event.target.checked);
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.timeKeepingSearchList.every(item => this.screenPropsSearch.setOfCheckedId.has(+item.timeKeepingDetailID));
    this.indeterminate = this.timeKeepingSearchList.some(item => this.screenPropsSearch.setOfCheckedId.has(+item.timeKeepingDetailID)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenPropsSearch.setOfCheckedId.add(id);
    } else {
      this.screenPropsSearch.setOfCheckedId.delete(id);
    }
  }

  checkAll(event) {
    this.timeKeepingSearchList.forEach(item => {
      this.updateCheckedSet(+item.timeKeepingDetailID, event.target.checked);
      this.refreshCheckedStatus();
    });
  }

  unCheckAll() {
    this.checked = false;
    this.timeKeepingSearchList.forEach(item => {
      this.screenPropsSearch.setOfCheckedId.delete(+item.timeKeepingDetailID);
    });
  }

  byPassViolation() {
    if (this.screenPropsSearch.setOfCheckedId.size > 0) {
      this.screenPropsSearch.setOfCheckedId.forEach(x => {
        this.screenPropsSearch.ids.push(x);
      });
      this.openPopup(this.screenPropsSearch.ids);
    } else {
      this.toastService.show('notification-message.unchecked', { classname: 'bg-warning text-light', delay: 3000 });
    }
  }

  // open popup confirm
  openPopup(ids: number[]) {
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components, https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalConfirmComponent, {centered: true});
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    this.translate.get('confirm-message.delete').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.result.then(result => {
      if (result === 'delete') {
        this.vm005003Service.deleteViolation(ids, Number(this.route.snapshot.params.id)).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp.error === null) {
            // show toast if delete success
            this.screenPropsSearch.ids = [];
            this.screenPropsSearch.setOfCheckedId.clear();
            this.checked = false;
            this.resetForm();
            //this.initDataSearch(null, this.localStorage.getPageSize(), this.formSearch.value);
            this.screenPropsSearch.page = 1;
            this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
          } else {
            // show toast if delete fail
            this.toastService.show('notification-message.delete-fail', { classname: 'bg-success text-light', delay: 3000 });
          }
        });
      }
    }, reason => {
    });
  }

  back(event: any) {
    this.router.navigate(['/timekeeping']);
  }
}
