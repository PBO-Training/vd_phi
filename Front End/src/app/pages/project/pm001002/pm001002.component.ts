import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbNavChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { parseDate } from '../../../common/datepicker-config/datepicker-config';
import { StorageService } from '../../../services/storage/storage.service';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import {
  DocumentResponse,
  DropDownDataIssue,
  DropDownDataMember,
  DropDownDataProjectInfo,
  Pm001002Response,
  ServerErrors
} from './pm001002-entity';
import { Pm001002Service } from './pm001002.service';
import { distinctUntilChanged, mergeMap, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { StoreTabService } from '../../../services/storage/storage-tab.service';
import { Pm001002StoreSerivce } from './pm001002-store.service';
import { ActionReviews } from '../../../common/constant/type';
import { Title } from '@angular/platform-browser';
import { TranslationService } from '../../../services/translate/translation.service';

@Component({
  selector: 'app-project-detail',
  templateUrl: './pm001002.component.html',
  styleUrls: ['./pm001002.component.scss']
})
export class Pm001002Component implements OnInit, OnDestroy {
  projectID = this.route.snapshot.params.id;
  isUpdate = this.projectID !== undefined ? true : false;
  activeId = this.projectID !== undefined ? 2 : 2;
  formChange: any;
  isDirty: boolean;
  public unsubscribe$ = new Subject();
  serverErrors = new Subject<ServerErrors>();
  // Input to child variable
  statusID: number;
  dropdownDataIssue: DropDownDataIssue;
  dropdownDataMember: DropDownDataMember;
  dropdownDataProjectInfo: DropDownDataProjectInfo;
  projectResponse: Pm001002Response;
  documentResponse: Array<DocumentResponse>;
  // Output from child variable
  // Overview
  navigateOrtherTab: number;
  listPositionProject: any;
  // Tab Project info
  saveProject: any;
  isCancel = false;
  isEditFromTabInfo = false;
  // Tab Issue
  isShowDetail: any;
  isEditFromTabIssue = false;
  // Tab member
  typeActionTabMember = 1;
  selectedMembers: any;
  isShowDetailDocument: any;
  isShowDetailMember: boolean;
  employeeID: number;
  constructor(
    private route: ActivatedRoute,
    private projectService: Pm001002Service,
    private modalService: NgbModal,
    public toastService: ToastService,
    private storageService: StorageService,
    private router: Router,
    public storeService: StoreTabService,
    private translate: TranslateService,
    private titleService: Title,
    public translationService: TranslationService,
    private pm001002GlobalStore: Pm001002StoreSerivce
  ) { }

  ngOnDestroy(): void {
    this.storageService.saveActiveTabProject(1);
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  ngOnInit(): void {
    // change title
    this.translate.get('title.project.project').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.project.project'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    if (this.route.snapshot.params.id === null || this.route.snapshot.params.id === undefined) {
      this.initDataActionCreate();
    } else {
      this.activeId = this.storageService.getActiveTabProject();
      this.storageService.saveActiveTabProject(this.activeId);
      this.initDataActionUpdate();
      switch (this.storageService.getProjectID()) {
        case null: {
          this.storageService.saveProjectID(Number(this.route.snapshot.params.id));
          break;
        }
        case Number(this.route.snapshot.params.id): {
          this.activeId = this.storageService.getActiveTabProject();
          this.storageService.saveProjectID(Number(this.route.snapshot.params.id));
          break;
        }
        // tslint:disable-next-line: no-switch-case-fall-through
        default: {
          this.activeId = this.activeId;
          this.storageService.saveProjectID(Number(this.route.snapshot.params.id));
          break;
        }
      }
      // this.getDocument();
      this.pm001002GlobalStore.tabMemberDetailStore.subscribe((val: any) => {
        this.isShowDetailMember = val.isShowDetailMember;
        this.employeeID = val.employeeID;
      });

    }

    this.storeService.dirtyCheck().pipe(takeUntil(this.unsubscribe$), distinctUntilChanged()).subscribe(val => {
      this.isDirty = val;
    });
    this.storeService.valueChanges.pipe(takeUntil(this.unsubscribe$), distinctUntilChanged()).subscribe(val => {
      this.formChange = val;
    });
  }

  /*
  * Init data for tabs
  */
  initDataActionCreate(): void {
    this.isEditFromTabInfo = true;
    this.projectService.pm001002Init().subscribe(
      (data: any) => {
        this.dropdownDataProjectInfo = data.content;
      },
      fail => {
        console.log('Pm001002TabInfoComponent -> initData -> err', fail);
      }
    );
  }

  initDataActionUpdate(): void {
    this.projectService.pm001002GetDetail(this.projectID).subscribe(
      (project: any) => {
        this.projectResponse = project.content;
      },
      err => {
        if (err.error.error.code.slice(10, 13) === '002') {
          this.router.navigate(['/404']);
        }
      }
    );
    this.projectService.pm001002Init().subscribe(
      (data: any) => {
        this.dropdownDataProjectInfo = data.content;
        this.listPositionProject = data.content.listPositionProject;
        this.isEditFromTabInfo = false;
      }
    );
    this.projectService.pm001002InitIssue().subscribe(
      (data: any) => {
        this.dropdownDataIssue = data.content;
      }
    );
    this.projectService.pm001002InitMember().subscribe(
      (data: any) => {
        this.dropdownDataMember = data.content;
      }
    );
  }

  /**
   * Tabs member
   */
  actionTabMember = (navigateActionTabMember: number) => {
    this.typeActionTabMember = navigateActionTabMember;
  }

  info = (selectedMembers: any) => {
    this.selectedMembers = selectedMembers;
    this.typeActionTabMember = 3;
  }

  receiveRequestRemove(request: any): void {
    this.projectService.removeMember(request).subscribe(
      (res: any) => {
        if (request.action !== ActionReviews.TEMPORARY) {
          // tslint:disable-next-line: forin
          for (const index in this.projectResponse.listEmployee) {
            request.listEmployee.forEach(element => {
              if (Number(element.employeeID) === Number(this.projectResponse.listEmployee[index].employeeID)) {
                this.projectResponse.listEmployee.splice(Number(index), 1);
              }
            });
          }
        }
        this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
        this.actionTabMember(1);
      }
    );
  }

  /**
   * Call API document
   */
  onShowDetailDocument = (value) => {
    this.isShowDetailDocument = value;
  }

  onReGetListDocument = (isGet) => {
    if (isGet) {
      this.getDocument();
    }
  }

  getDocument = () => {
    this.projectService.pm001002GetDocument(this.projectID).subscribe(
      (res: any) => {
        this.documentResponse = res.content;
      }
    );
  }

  /*
  * Tab issue
  */
  showDetail = (navigateToDetail: any) => {
    if (navigateToDetail.issueID === null) {
      this.isEditFromTabIssue = true;
    }
    this.isCancel = true;
    this.isShowDetail = navigateToDetail;
  }

  onReGetProjectDetail(value: boolean): void {
    if (value === true) {
      this.initDataActionUpdate();
    }
  }

  onEditFromTabIssue(value: boolean): void {
    this.isEditFromTabIssue = value;
  }

  /*
  * Tab Project info
  */
  receiveActionSave = (dataFromChild: any) => {
    this.saveProject = dataFromChild;
    this.onSaveProject();
  }

  onSaveProject = () => {
    const value = JSON.parse(this.formChange);
    const request = {
      ...value,
      projectStartDate: parseDate(value.projectStartDate),
      projectEndDate: parseDate(value.projectEndDate)
    };

    if (value.isValid) {
      if (this.saveProject && this.saveProject === 'create') {
        this.projectService.pm001002Create(request).subscribe(
          (res: any) => {
            this.router.navigate(['/project/detail/', res.content.projectID]);
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
          },
          errors => {
            this.serverErrors.next(errors.error.error);
            this.serverErrors.asObservable();
          }
        );
      } else {
        this.projectService.pm001002Update(request).subscribe(
          (res: any) => {
            this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
            this.initDataActionUpdate();
            this.isDirty = false;
          },
          errors => {
            this.serverErrors.next(errors.error.error);
            this.serverErrors.asObservable();
          }
        );
      }
    }
  }

  onEditFromTabInfo(event: boolean): void {
    this.isEditFromTabInfo = event;
  }

  onCancelFromTabInfo(event: boolean): void {
    this.isCancel = event;
    if (this.isDirty) {
      this.onShowPopup();
    } else {
      this.isEditFromTabInfo = false;
    }
  }

  /*
  * Process list tabs
  */
  onReceiveActiveIDFromOverview(activeID): void {
    this.activeId = activeID;
    // change tab return list member
    if (activeID === 5) {
      this.typeActionTabMember = 1;
    }
  }

  public beforeChange($event: NgbNavChangeEvent) {
    if (this.isShowDetail) {
      this.isShowDetail['value'] = false;
    }
    if (this.isShowDetailMember) {
      this.isShowDetailMember = false;
    }
    if (!this.isDirty) {
      this.isEditFromTabInfo = false;
      this.activeId = $event.nextId;
      if ($event.nextId === 5) {
        this.typeActionTabMember = 1;
      }
      this.storageService.saveActiveTabProject(this.activeId);
      return;
    }

    if ($event.activeId === 2) {
      // this.isEditFromTabInfo = false;
      $event.preventDefault();
      const modalRef = this.modalService.open(ModalConfirmComponent);
      this.translate.get('confirm-message.update').subscribe(
        (text: string) => {
          modalRef.componentInstance.title = text;
        }
      );
      modalRef.componentInstance.isDelete = false;
      modalRef.componentInstance.swapTab = true;
      modalRef.result.then(
        result => {
          if (result === 'Close click') {
            this.isEditFromTabInfo = false;
            this.activeId = $event.nextId;
            this.isDirty = false;
            this.storageService.saveActiveTabProject(this.activeId);
            return;
          }

          if (result === 'Close') {
            this.isEditFromTabInfo = true;
            this.storageService.saveActiveTabProject(this.activeId);
            return;
          }

          switch (this.activeId) {
            case 2:
              const value = JSON.parse(this.formChange);
              if (value.isValid) {
                this.onSaveProject();
                this.activeId = $event.nextId;
                this.storageService.saveActiveTabProject(this.activeId);
              }
              break;

            default:
              break;
          }

        },
        reason => { }
      );
    }
    if ($event.nextId === 5) {
      this.typeActionTabMember = 1;
    }

    if ($event.nextId === 2) {
      this.activeId = $event.nextId;
    }
    this.storageService.saveActiveTabProject($event.nextId);
    this.isShowDetail = false;
    // change tab then return list document
    this.isShowDetailDocument = false;
  }

  onShowPopup(): void {
    const modalRef = this.modalService.open(ModalConfirmComponent);
    this.translate.get('confirm-message.update').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.componentInstance.isDelete = false;
    modalRef.componentInstance.swapTab = true;
    modalRef.result.then(
      result => {
        if (result === 'Close') {
          return;
        }
        if (result === 'Close click') {
          if (this.isCancel === true) {
            this.isEditFromTabInfo = false;
            this.isDirty = false;
          } else {
            this.router.navigate(['/project']);
          }
          return;
        }
        if (result === 'save tab') {
          switch (this.activeId) {
            case 2:
              const value = JSON.parse(this.formChange);
              if (value.isValid === true) {
                this.onSaveProject();
                this.isDirty = false;
                if (this.isCancel === true) {
                  this.isDirty = false;
                  this.isEditFromTabInfo = false;
                } else {
                  this.router.navigate(['/project']);
                }
              }
              break;
            default:
              break;
          }
        }
      },
      reason => { }
    );
  }

  onBack(event: boolean) {
    this.isCancel = false;
    if (event && this.isDirty) {
      this.onShowPopup();
    } else {
      this.router.navigate(['/project']);
    }
  }

  onNavigateIssueDetailFromMemberDetail(ID: number) {
    this.activeId = 3;
    this.isShowDetail = {
      value: true,
      issueID: ID
    };
  }

  onNavigateMemberDetailFromOverview(empID: number) {
    this.activeId = 5;
    this.isShowDetailMember = true;
    this.employeeID = empID;
  }
}
