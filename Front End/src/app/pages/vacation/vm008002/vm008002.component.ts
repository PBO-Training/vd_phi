import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from 'src/app/services/translate/translation.service';
import * as type from '../../../common/constant/type';
import { Listprops } from '../../../common/page-options';
import { StorageService } from '../../../services/storage/storage.service';
import {
  ModalNotificationMaintenanceComponent
} from '../../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { VM008002Service } from './vm008002.service';

@Component({
  selector: 'app-vm008002',
  templateUrl: './vm008002.component.html'
})
export class VM008002Component implements OnInit {

  accessEmployeeName: string;
  accessEmployeeID: string;
  selectedLeader: any[] = [];
  selectedManager: any[] = [];
  selectedForward: any[] = [];

  listOption: any[] = [];
  selectedToOrigin: any[] = [];

  submitted: boolean;
  isCheck: boolean;
  isDataAvailable: boolean = false;

  Type: any = type;

  screenProps = new Listprops();

  formSubmit: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private localStorage: StorageService,
    private route: ActivatedRoute,
    private vm008002Service: VM008002Service,
    private router: Router,
    private datepipe: DatePipe,
    private modalService: NgbModal,
    private titleService: Title,
    private translate: TranslateService,
    public translationService: TranslationService,

  ) {
    this.readstorageData();
    this.isCheck = true;
    const id = this.route.snapshot.params.id;
    this.screenProps.loading = true;
    this.submitted = false;

    this.initForm();
    this.initDataLoad(id);
  }

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.shift-work-history').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.shift-work-history'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
  }

  get f() { return this.formSubmit.controls; }
  private unsubscribe$ = new Subject();

  /** Initialize a submit form **/
  initForm(): void {
    this.formSubmit = this.formBuilder.group({
      accessEmployeeID: null,
      swID: null,
      swOptionID: null,
      swStartDateAM: null,
      swEndDatePM: null,
      swReason: null,
      swGroupNote: null,
      isApprove: null,
      swAssignGroupOne: null,
      swAssignGroupTwo: null,
      swAssignGroupThree: null,
      statusNameGroupOne: null,
      statusNameGroupTwo: null,
      statusNameGroupThree: null,
      swAssignGroupOneNote: null,
      swAssignGroupTwoNote: null,
      swAssignGroupThreeNote: null,
      swUpdateGroupOneDate: null,
      swUpdateGroupTwoDate: null,
      swUpdateGroupThreeDate: null
    });
  }

  /** Check process and initialize data **/
  initDataLoad(id) {
    this.vm008002Service.vm008002InitList().pipe(takeUntil(this.unsubscribe$)).subscribe((data: any) => {
      if (data.error === null) {
        this.listOption = data.content.listShiftWorkOption;
        this.selectedToOrigin = data.content.listEmployee;
        if (!id) {
          if (this.listOption != null) {
            this.formSubmit.patchValue({
              swOptionID: this.listOption[0]?.swOptionID,
            });
          }
        }
        this.initDetailData(id);
      }
      else {
        this.openPopupIsDataAvailable('notification.500');
      }
    });
  }

  /** Initialize data when process is get detail **/
  initDetailData(id) {
    let getNum: number = id;
    let isNum = Number(getNum);
    if (typeof isNum === 'number' && (isNum % 1) === 0) {
      this.vm008002Service.vm008002GetDetail(id, this.accessEmployeeID).pipe(takeUntil(this.unsubscribe$)).subscribe((value) => {
        if (value) {
          if (value.content == null) {
            this.router.navigate(['/404']);
          }
          else {
            this.isDataAvailable = true;

            let getStartTime = new Date(value.content.swStartDateAM).toISOString().split('.')[0];
            let getEndTime = new Date(value.content.swEndDatePM).toISOString().split('.')[0];

            this.selectedLeader = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.swAssignGroupOne);
            this.selectedManager = this.selectedToOrigin.filter(a => a.keyResponse.toString() == value.content.swAssignGroupTwo);

            var splitted = value.content.swAssignGroupThree.split(";");
            this.selectedForward = this.selectedToOrigin.filter(array => splitted.some(filter => filter === array.keyResponse.toString()));

            this.formSubmit.patchValue({
              swID: value.content.swID,
              swOptionID: value.content.swOptionID,
              swStartDateAM: this.datepipe.transform(getStartTime, 'dd-MM-yyyy'),
              swEndDatePM: this.datepipe.transform(getEndTime, 'dd-MM-yyyy'),
              swReason: value.content.swReason,
              statusNameGroupOne: value.content.statusNameGroupOne,
              statusNameGroupTwo: value.content.statusNameGroupTwo,
              statusNameGroupThree: value.content.statusNameGroupThree,
              swUpdateGroupOneDate: value.content.swUpdateGroupOneDate ? new Date(value.content.swUpdateGroupOneDate).toLocaleString() : null,
              swUpdateGroupTwoDate: value.content.swUpdateGroupTwoDate ? new Date(value.content.swUpdateGroupTwoDate).toLocaleString() : null,
              swUpdateGroupThreeDate: value.content.swUpdateGroupThreeDate ? new Date(value.content.swUpdateGroupThreeDate).toLocaleString() : null,
              swAssignGroupOneNote: value.content.swAssignGroupOneNote,
              swAssignGroupTwoNote: value.content.swAssignGroupTwoNote,
              swAssignGroupThreeNote: value.content.swAssignGroupThreeNote,
            });
          }
        }
      },
        err => {
          if (err) {
            this.router.navigate(['/403']);
          }
        });
    } else {
      this.router.navigate(['/404']);
    }
  }

  /** Process when click on Back button **/
  back(event: any) {
    this.router.navigate(['/shift-work-history']);
  }

  /** Find user login **/
  readstorageData() {
    const storage: any = this.localStorage.getUser();
    this.accessEmployeeID = storage.employeeID;
    this.accessEmployeeName = storage.employeeName;
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

}
