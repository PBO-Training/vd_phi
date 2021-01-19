import { AfterViewChecked, ChangeDetectorRef, Component, ElementRef, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbNavChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Observable, Subject } from 'rxjs';
import { distinctUntilChanged, mergeMap, takeUntil } from 'rxjs/operators';
import { StoreSystemService } from '../../../services/store/store-system.service';
import { parseDate } from '../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../common/screen-action/screen-action';
import { CommonFunctionService } from '../../../services/common-function/common-function.service';
import { StorageService } from '../../../services/storage/storage.service';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { EmployeeDetails, InitEmployee, RequestUpdate, ServerErrors } from './em001002-entity';
import { StoreService } from './em001002-store-service';
import { Em001002Service } from './em001002.service';
import { Title } from '@angular/platform-browser';
import { TranslationService } from '../../../services/translate/translation.service';

@Component({
  selector: 'app-em001002',
  templateUrl: './em001002.component.html',
  styleUrls: ['./em001002.component.scss']
})
export class Em001002Component implements OnInit, OnDestroy, AfterViewChecked {
  active = 10;
  nextTab: number;
  isUpdate: boolean;
  disabled = true;
  employeeId: number;
  masterData$: Observable<InitEmployee>;
  employeeDetails: EmployeeDetails | null;
  employeeDetails$: Observable<EmployeeDetails>;
  isSubmit: boolean;
  isDirty: boolean;
  public unsubscribe$ = new Subject();
  reset: boolean;
  formChange: any;
  authButton = new ScreenAction();
  companyName: string;
  isAddNewHistory = false;
  isAddNewContract = false;
  isAddNewSkill = false;
  isAddNewLanguage = false;
  isEditablePersonal = false;
  isEditableHistory = false;
  isEditableContract = false;
  isEditableSkill = false;
  isEditableLanguage = false;
  isHiddenContract = false;
  isBackCreate = false;
  isProfile = false;
  formSubmitSkill = false;
  isEditFormSkill = false;
  contractFile: any;
  serverErrors = new Subject<ServerErrors>();
  request: RequestUpdate = {
    tabIndex: this.active,
    employeeID: 0,
    contractRequest: {},
    historyRequest: [],
    languageRequest: [],
    skillRequest: [],
    personalRequest: {}
  };
  constructor(
    private employeeService: Em001002Service,
    private route: ActivatedRoute,
    public toastService: ToastService,
    private router: Router,
    public storeService: StoreService,
    private modalService: NgbModal,
    private commonFunctionService: CommonFunctionService,
    private translate: TranslateService,
    private storageService: StorageService,
    private el: ElementRef,
    private cdr: ChangeDetectorRef,
    private storeSystemService: StoreSystemService,
    private titleService: Title,
    public translationService: TranslationService,
  ) {
    this.employeeId = this.route.snapshot.params.id;
    if (this.employeeId) {
      this.disabled = false;
      this.isUpdate = true;
      // get details
      this.getDetailsEdit();
    } else {
      this.isEditablePersonal = true;
      this.isEditableHistory = true;
      this.isEditableContract = true;
      this.isEditableSkill = true;
      this.isEditableLanguage = true;
    }
    const url = this.router.url;
    if (url.indexOf('profile') > 0) {
      const user: any = this.storageService.getUser();
      if (+user.employeeID !== +this.employeeId) {
        this.router.navigateByUrl('/403');
        return;
      }
      this.authButton.CREATE = true;
      this.authButton.UPDATE = true;
      this.isHiddenContract = true;
      this.isProfile = true;
    } else {
      this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
      this.isHiddenContract = false;
    }
    // get companycode
    const authUser = this.storageService.getUser();
    // tslint:disable-next-line:no-string-literal
    this.companyName = authUser['companyName'];
  }

  ngAfterViewChecked(): void {
    this.cdr.detectChanges();
  }

  ngOnDestroy(): void {
    this.storageService.saveActiveTabEmployee(10);
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  onNavChange(changeEvent: NgbNavChangeEvent) {
    // change tab return view page
    if (changeEvent.activeId === 10 && this.isEditablePersonal === false && this.isDirty === true) {
      this.isDirty = false;
    }
    // if (changeEvent.activeId === 20 && this.isEditableSkill === false && this.isDirty === true) {
    //   this.isDirty = false;
    // }
    if (changeEvent.activeId === 20 && !this.isEditFormSkill && this.isDirty === true) {
      this.isDirty = false;
    }
    if (changeEvent.activeId === 30 && this.isEditableLanguage === false && this.isDirty === true) {
      this.isDirty = false;
    }
    if (changeEvent.activeId === 40 && this.isEditableHistory === false && this.isDirty === true) {
      this.isDirty = false;
    }
    if (changeEvent.activeId === 50 && this.isEditableContract === false && this.isDirty === true) {
      this.isDirty = false;
    }
    // if (changeEvent.activeId === 60 && !this.isEditFormSkill && this.isDirty === true) {
    //   this.isDirty = false;
    // }
    if (changeEvent.nextId === 10) {
      this.isEditablePersonal = false;
    }
    // if (changeEvent.nextId === 20) {
    //   this.isEditableSkill = false;
    // }
    if (changeEvent.nextId === 30) {
      this.isEditableLanguage = false;
    }
    if (changeEvent.nextId === 40) {
      this.isEditableHistory = false;
    }
    if (changeEvent.nextId === 50) {
      this.isEditableContract = false;
    }
    if (!this.isDirty) {
      this.active = changeEvent.nextId;
      this.storageService.saveActiveTabEmployee(this.active);
      return;
    }
    changeEvent.preventDefault();
    // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components,
    // https://angular.io/api/core/ViewContainerRef;
    const modalRef = this.modalService.open(ModalConfirmComponent, { centered: true });
    modalRef.componentInstance.title = 'confirm-message.update';
    modalRef.componentInstance.isDelete = false;
    modalRef.componentInstance.swapTab = true;
    modalRef.componentInstance.submitForm.pipe(takeUntil(this.unsubscribe$)).subscribe(val => {
      this.storeService.isSubmit.next(val);

    });
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    modalRef.result.then(result => {
      if (result === 'Close click') {
        this.active = changeEvent.nextId;
        this.storageService.saveActiveTabEmployee(this.active);
        return;
      }
      if (result === 'save tab' && !this.isSubmit && this.active === 20) {
        this.storeService.isSubmitFormSkill.next(true);
        return;
      }
      if (result === 'Close' || this.isSubmit === false) {
        return;
      }

      this.nextTab = changeEvent.nextId;
      switch (this.active) {
        case 10:
          this.submitPersonalEdit(JSON.parse(this.formChange));
          break;
        // case 20:
        //   this.submitSkillEdit(JSON.parse(this.formChange));
        //   break;
        case 20:
          this.submitSkillLanguage(JSON.parse(this.formChange));
          break;
        case 30:
          this.submitLanguageEdit(JSON.parse(this.formChange));
          break;
        case 40:
          this.submitHistoryEdit(JSON.parse(this.formChange));
          break;
        case 50:
          if (this.contractFile) {
            this.submitContractEdit(JSON.parse(this.formChange));
          }
          break;

        default:
          // TODO: save form active next tab
          break;
      }
    }, reason => {
    });
  }

  openPopupNew() {
    if (this.authButton.CREATE === true || this.authButton.UPDATE === true) {
      // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components
      // https://angular.io/api/core/ViewContainerRef;
      const modalRef = this.modalService.open(ModalConfirmComponent, { centered: true });
      modalRef.componentInstance.isDelete = false;
      modalRef.componentInstance.isBack = true;
      modalRef.componentInstance.submitForm.pipe(takeUntil(this.unsubscribe$)).subscribe(val => {
        this.storeService.isSubmit.next(val);
      });
      // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
      this.translate.get('confirm-message.update').subscribe(
        (text: string) => {
          modalRef.componentInstance.title = text;
        }
      );
      modalRef.result.then(result => {
        if (result === 'Close') {
          return;
        }
        if (result === 'Unsave' && !this.isSubmit) {
          if (this.isBackCreate) {
            this.storageService.saveBackFlag(true);
            this.router.navigate(['/employee']);
          }
          this.isEditablePersonal = false;
          this.isEditableHistory = false;
          this.isEditableContract = false;
          // this.isEditableSkill = false;
          this.storeService.isEditFormSkill.next(false);
          this.isEditableLanguage = false;
          return;
        }
        if (result === 'save tab') {
          if (!this.isSubmit && this.active === 20) {
            this.storeService.isSubmitFormSkill.next(true);
            return;
          }
          if (this.isDirty && this.isSubmit) {
            switch (this.active) {
              case 10:
                this.submitPersonalEdit(JSON.parse(this.formChange));
                break;
              // case 20:
              //   this.submitSkillEdit(JSON.parse(this.formChange));
              //   break;
              case 20:
                this.submitSkillLanguage(JSON.parse(this.formChange));
                break;
              case 30:
                this.submitLanguageEdit(JSON.parse(this.formChange));
                break;
              case 40:
                this.submitHistoryEdit(JSON.parse(this.formChange));
                break;
              case 50:
                if (this.contractFile) {
                  this.submitContractEdit(JSON.parse(this.formChange));
                }
                break;

              default:
                // TODO: save form active next tab
                // this.active = changeEvent.nextId;
                break;
            }
          }
        } else {
          this.isEditablePersonal = false;
          this.isEditableHistory = false;
          this.isEditableContract = false;
          // this.isEditableSkill = false;
          this.storeService.isEditFormSkill.next(false);
          this.isEditableLanguage = false;
        }
      }, reason => {
      });
    }
  }
  ngOnInit(): void {
    // change title
    this.translate.get('title.employee.employee').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.employee.employee'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // get status changes of form in component children
    this.storeService.isValidSubmit$.subscribe((status: boolean) => {
      this.isSubmit = status;
    });
    this.active = this.storageService.getActiveTabEmployee();
    this.storageService.saveActiveTabEmployee(this.active);

    this.storeService.valueChanges.pipe(takeUntil(this.unsubscribe$), distinctUntilChanged()).subscribe(val => {
      this.formChange = val;
    });
    this.storeService.dirtyCheck().pipe(takeUntil(this.unsubscribe$), distinctUntilChanged()).subscribe(val => {
      this.isDirty = val;
    });
    this.masterData$ = this.employeeService.em001002init();
    this.storeService.isEditFormSkill.subscribe((val: boolean) => {
      this.isEditFormSkill = val;
    });
  }

  back(event: any) {
    if (this.isDirty) {
      this.openPopupNew();
    } else {
      this.storageService.saveBackFlag(true);
      this.router.navigate(['/employee']);
    }
  }

  backOrCancel(event: any) {
    if (this.isDirty) {
      this.openPopupNew();
    } else {
      this.isEditablePersonal = false;
      this.isEditableHistory = false;
      this.isEditableContract = false;
      // this.isEditableSkill = false;
      this.storeService.isEditFormSkill.next(false);
      this.isEditableLanguage = false;
    }
  }

  backEdit(event: any) {
    this.storageService.saveBackFlag(true);
    this.router.navigate(['/employee']);
  }

  onEditPersonal(event: any) {
    this.isEditablePersonal = event;
  }

  onEditHistory(event: any) {
    this.isEditableHistory = event;
  }

  onEditContract(event: any) {
    this.isEditableContract = event;
  }

  onEditSkill(event: any) {
    this.isEditableSkill = event;
  }

  onEditLanguage(event: any) {
    this.isEditableLanguage = event;
  }

  submitPersonalEdit(event: any) {
    const body: RequestUpdate = {
      ...this.request,
      employeeID: this.employeeId,
      personalRequest:
      {
        ...event,
        birthday: parseDate(event.birthday),
        dateOfIssueOfIDCard: parseDate(event.dateOfIssueOfIDCard),
        dateJoinCompany: parseDate(event.dateJoinCompany)
      },
    };
    if (this.employeeId) {
      this.employeeService.em001002update(body).subscribe(
        (response) => {
          if (!response.error) {
            // set avatar and full name nav-right.component
            const user: any = this.storageService.getUser();
            if (+user.employeeID === +this.employeeId) {
              this.storeSystemService.setAvataSubject(body.personalRequest.avataUrl);
              this.storeSystemService.setFullNameSubject(body.personalRequest.firstName + ' ' + body.personalRequest.lastName);
              user.avataURL = body.personalRequest.avataUrl;
              user.firstName = body.personalRequest.firstName;
              user.lastName = body.personalRequest.lastName;
              user.employeeName = body.personalRequest.firstName + ' ' + body.personalRequest.lastName;
              this.storageService.saveUser(user);
            }

            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            if (this.nextTab) {
              this.active = this.nextTab;
            }
            this.getDetailsEdit();
          }
        },
        (errors) => {
          this.serverErrors.next(errors.error.error);
          this.serverErrors.asObservable();
        });
    } else {
      this.employeeService.em001002create(body.personalRequest).subscribe(
        (response) => {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          // bug
          this.router.navigate(['/employee/detail/', response.content.employeeID]);
        },
        (errors) => {
          this.serverErrors.next(errors.error.error);
          this.serverErrors.asObservable();
        }
      );
    }
  }

  submitHistoryEdit(event: any) {
    const formValue = event.listHistory;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < formValue.length; i++) {
      formValue[i].historyStartDate = parseDate(formValue[i].historyStartDate);
      formValue[i].historyEndDate = parseDate(formValue[i].historyEndDate);
    }
    // TODO: Submit call api
    const body: RequestUpdate = {
      ...this.request,
      employeeID: this.employeeId,
      historyRequest: event.listHistory,
      tabIndex: this.active
    };
    this.employeeService.em001002update(body).subscribe(
      (response) => {
        if (!response.error) {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          if (this.nextTab) {
            this.active = this.nextTab;
          }
          this.getDetailsEdit();
          this.isAddNewHistory = false;
        }
      },
      (errors) => {
        this.serverErrors.next(errors.error.error);
        this.serverErrors.asObservable();
      }
    );
  }

  submitContractEdit(event: any) {
    const body: RequestUpdate = {
      ...this.request,
      employeeID: this.employeeId,
      contractRequest: {
        ...event,
        contractStartDate: parseDate(event.contractStartDate),
        contractEndDate: parseDate(event.contractEndDate),
        contractAttachments: this.contractFile
      },
      tabIndex: this.active
    };
    this.employeeService.em001002update(body).subscribe(
      (response) => {
        if (!response.error) {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          if (this.nextTab) {
            this.active = this.nextTab;
          }
          this.getDetailsEdit();
          this.isAddNewContract = false;
        }
      },
      (errors) => {
        this.serverErrors.next(errors.error.error);
        this.serverErrors.asObservable();
      }
    );
  }

  submitSkillEdit(event: any) {
    const body: RequestUpdate = {
      ...this.request,
      employeeID: this.employeeId,
      skillRequest: event.listSkill,
      tabIndex: this.active
    };
    this.employeeService.em001002update(body).subscribe(
      (response) => {
        if (!response.error) {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          if (this.nextTab) {
            this.active = this.nextTab;
          }
          // this.router.navigate(['/employee']);
          this.getDetailsEdit();
          this.isAddNewSkill = false;
        }
      },
      (errors) => {
        this.serverErrors.next(errors.error.error);
        this.serverErrors.asObservable();
      }
    );
  }

  submitFormSkill(event: any) {
    const body: RequestUpdate = {
      ...this.request,
      employeeID: this.employeeId,
      skillRequest: event.listSkill,
      tabIndex: this.active
    };
    this.employeeService.em001002update(body).subscribe(
      (response) => {
        if (!response.error) {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          if (this.nextTab) {
            this.active = this.nextTab;
          }
          // this.router.navigate(['/employee']);
          this.getDetailsEdit();
        }
      },
      (errors) => {
        this.serverErrors.next(errors.error.error);
        this.serverErrors.asObservable();
      }
    );
  }

  submitLanguageEdit(event: any) {
    const formValue = event.listLanguage;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < formValue.length; i++) {
      formValue[i].expirationDate = parseDate(formValue[i].expirationDate);
    }
    // TODO: Submit call api
    const body: RequestUpdate = {
      ...this.request,
      employeeID: this.employeeId,
      languageRequest: event.listLanguage,
      tabIndex: this.active
    };
    this.employeeService.em001002update(body).subscribe(
      (response) => {
        if (!response.error) {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          if (this.nextTab) {
            this.active = this.nextTab;
          }
          this.getDetailsEdit();
          this.isAddNewLanguage = false;
        }
      },
      (errors) => {
        this.serverErrors.next(errors.error.error);
        this.serverErrors.asObservable();
      }
    );
  }

  submitSkillLanguage(formValue: any) {
    const body: RequestUpdate = {
      ...this.request,
      employeeID: this.employeeId,
      skillRequest: formValue.listSkill,
      tabIndex: this.active
    };
    this.employeeService.em001002update(body).subscribe(
      (response) => {
        if (!response.error) {
          this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          if (this.nextTab) {
            this.active = this.nextTab;
          }
          this.getDetailsEdit();
        }
      },
      (errors) => {
        this.serverErrors.next(errors.error.error);
        this.serverErrors.asObservable();
      }
    );
  }

  getDetailsEdit() {
    this.nextTab = 0;
    // TODO: fix async-issue-with-child-components details
    this.employeeService.em001002details({ employeeID: this.employeeId }).subscribe(
      response => {
        if (response.content) {
          this.employeeDetails = response;
          this.isEditablePersonal = false;
          this.isEditableHistory = false;
          this.isEditableContract = false;
          this.isEditableSkill = false;
          this.isEditableLanguage = false;
          this.isDirty = false;
        }
        if (response.content === null) {
          this.router.navigateByUrl('/404');
          return;
        }
      }
    );
  }


  onAddNewHistory(event: any) {
    this.isDirty = event;
    this.isAddNewHistory = event;
    this.isEditableHistory = event;
  }

  onAddNewContract(event: any) {
    // this.isDirty = event;
    this.isAddNewContract = event;
    this.isEditableContract = event;
  }

  onAddNewSkill(event: any) {
    this.isDirty = event;
    this.isAddNewSkill = event;
    this.isEditableSkill = event;
  }

  contractFileToBase64($event): any {
    this.contractFile = $event;
  }

  onAddNewLanguage(event: any) {
    this.isDirty = event;
    this.isAddNewLanguage = event;
    this.isEditableLanguage = event;
  }

  backCreate(event: any) {
    this.isBackCreate = event;
  }

  // check direct to detail of unknow employee
  isExistEmployee(employeeIDToCheck: number) {
    this.employeeService.em001002details({ employeeID: employeeIDToCheck }).subscribe(
      response => {
        if (response.content === null) {
          return false;
        }
      }
    );
    return true;
  }
}

