import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import * as type from '../../../common/constant/type';
import { Listprops } from '../../../common/page-options';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { TimeKeeping } from './vm005001-entity';
import { VM005001Service } from './vm005001.service';
import { Router } from '@angular/router';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { TranslateService } from '@ngx-translate/core';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-vm005001',
  templateUrl: './vm005001.component.html',
  styleUrls: ['./vm005001.component.scss']
})
export class VM005001Component implements OnInit {

  displayMessage: string;
  errorsCode: string;
  isCollapsed = false;
  submitted: boolean;
  isDataAvailable: boolean = false;

  formSearch: FormGroup;
  formBaseSend: FormGroup;
  timeKeepingList: TimeKeeping[];
  yearList: any[] = [];

  screenProps = new Listprops();
  Type: any = type;

  valueOldForSend = {
    year: new Date().getFullYear()
  };
  authButton = new ScreenAction();

  constructor(
    private formBuilder: FormBuilder,
    private vm005001Service: VM005001Service,
    private localStorage: StorageService,
    public translationService: TranslationService,
    public toastService: ToastService,
    private router: Router,
    private commonFunctionService: CommonFunctionService,
    private titleService: Title,
    private translate: TranslateService,

  ) {
    this.readstorageData();
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.initForm();
    this.setYearList();
    this.formBaseSend = this.formBuilder.group({
      year: new Date().getFullYear()
    });
    this.initDataTimeKeeping(this.formBaseSend);
  }

  get f() { return this.formSearch.controls; }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {
    // change title
    this.translate.get('title.vacation.timekeeping').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.timekeeping'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

  }

  readstorageData() {
    const storage: any = this.localStorage.getUser();
  }

  setYearList() {
    this.yearList = Array<type.Year>();
    let end = new Date().getFullYear();
    for (let i = end; i >= 1900; i--) {
      this.yearList.push(new type.Year(i, i + " "));
    }
  }

  initForm(): void {
    this.formSearch = this.formBuilder.group({
      year: new Date().getFullYear(),
    });
  }

  resetForm() {
    this.initForm();
    this.formBaseSend.patchValue({
      year: new Date().getFullYear(),
    });
    this.initDataTimeKeeping(this.formBaseSend);
  }

  initDataTimeKeeping(form: FormGroup) {
    this.submitted = true;
    const formData = {
      ...form.value,
    };
    if (this.formSearch.valid) {
      this.submitted = false;
      this.vm005001Service.vm005001searchTimeKeeping(formData).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
        if (datacheck.error === null) {
          this.timeKeepingList = datacheck.content.data;
          this.screenProps.loading = false;
        }
      }, err => {
        const httpErr = err.error;
        this.timeKeepingList = [];
        if (!httpErr) {
          return;
        }
        else {
          this.displayErrorMessage(httpErr);
        }
      });
    }
  }

  searchTimeKeepingList() {
    this.screenProps.loading = true;
    this.valueOldForSend = JSON.parse(JSON.stringify(this.formSearch.value));
    this.formBaseSend.value.year = this.valueOldForSend.year;
    this.initDataTimeKeeping(this.formBaseSend);
  }

  resetTimeKeeping(id: number) {
    this.vm005001Service.resetTimeKeeping(id).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
      if (resp.error === null) {
        this.resetForm();
        this.initDataTimeKeeping(this.formBaseSend);
        this.screenProps.page = 1;
        this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
      } else {
        // show toast if delete fail
        this.toastService.show('notification-message.delete-fail', { classname: 'bg-success text-light', delay: 3000 });
      }
    })
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

  import(event: any) {
    this.router.navigate(['/timekeeping/import/' + event]);
  }
}
