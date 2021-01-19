import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { TranslationService } from '../../../services/translate/translation.service';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { Action, GroupScreen, Screen } from './ms017002-entity';
import { ActionRequest, RoleScreenRequest } from './ms017002-request';
import { MS017002Service } from './ms017002.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { TranslateService } from '@ngx-translate/core';

export interface Permission {
  groupScreenCode: string;
  isCollapsed: boolean;
  listRoleAction?: any[];
}

export interface RoleAction {
  groupScreenCode: string;
}

@Component({
  selector: 'app-ms017002',
  templateUrl: './ms017002.component.html',
  styleUrls: ['./ms017002.component.scss'],
  entryComponents: [ModalConfirmComponent],
})
export class MS017002Component implements OnInit {
  formData: FormGroup;
  listAction: Action[] = [];
  listGroupAction = [];
  listPermission: Permission[] = [];
  listPermissionOld: Permission[] = [];
  idDetail = '';
  typeRole = true;
  listRoleScreenRequest: RoleScreenRequest[] = [];
  private unsubscribe$ = new Subject();
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private ms017002Service: MS017002Service,
    public toastService: ToastService,
    public translationService: TranslationService,
    private translate: TranslateService,
    private modalService: NgbModal) { }

  // tslint:disable-next-line:use-lifecycle-interface
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  ngOnInit(): void {
    this.idDetail = this.getTypeDetail(this.route.snapshot.params.id, true);
    const sTypeDetail = this.getTypeDetail(this.route.snapshot.params.id);
    if (sTypeDetail === 'role') {
    } else if (sTypeDetail === 'screen') {
      this.typeRole = false;
      this.router.navigateByUrl('/404');
      return;
    } else {
      this.router.navigateByUrl('/404');
      return;
    }
    this.formData = this.fb.group({
      roleCode: [this.idDetail],
      screenCode: [this.idDetail],
    });
    this.ms017002Service.ms017002Init().subscribe(
      data => {
        if (data.content) {
          this.listAction = [{ actionCode: '' }, { actionCode: 'VIEW' }, ...data.content.listAction];
          data.content.listGroupScreen.forEach((groupScreen: GroupScreen) => {
            this.listPermission.push({ groupScreenCode: groupScreen.groupScreenCode, isCollapsed: true, listRoleAction: [] });
          });
          data.content.listScreen.forEach((screen: Screen) => {
            this.listPermission.forEach(group => {
              if (screen.groupScreenCode === group.groupScreenCode) {
                const object = { screenCode: screen.screenCode, screenUrl: screen.screenUrl, VIEW: false };
                data.content.listAction.forEach((action: Action) => {
                  object['disable' + action.actionCode] = true;
                });
                group.listRoleAction.push(object);
              }
            });
          });
          // get detail Role
          this.ms017002Service.ms017002GetDetail({ roleCode: this.getTypeDetail(this.route.snapshot.params.id, true) }).subscribe(
            res => {
              if (res.content) {
                res.content[0].listScreen.forEach(element => {
                  const objectPermission = this.listPermission.find(permisstion => permisstion.groupScreenCode === element.groupScreenCode);
                  if (objectPermission) {
                    // tslint:disable-next-line: max-line-length
                    const objectRoleAction = objectPermission.listRoleAction.find(roleAction => roleAction.screenCode === element.screenCode);
                    if (objectRoleAction) {
                      objectRoleAction.VIEW = element.access; // objectRoleAction['VIEW'] = element.access;
                      data.content.listAction.forEach((action: Action) => {
                        const objectAction = element.listAction.find(item => action.actionCode === item.actionCode);
                        if (!objectAction) {
                          objectRoleAction['disable' + action.actionCode] = true;
                        } else {
                          objectRoleAction[action.actionCode] = objectAction.access;
                          objectRoleAction['disable' + action.actionCode] = false;
                        }
                      });
                    }
                    // else {
                    //   const object = { screenCode: element.screenCode, VIEW: false };
                    //   data.content.listAction.forEach((action: Action) => {
                    //     object['disable' + action.actionCode] = true;
                    //   });
                    //   objectPermission.listRoleAction.push(object);
                    // }
                  }
                });
              }
              this.listPermissionOld = JSON.parse(JSON.stringify(this.listPermission));
            }
          );
        }
      }
    );
  }

  onChange(actionCode, screenCode, groupScreenCode, event) {
    const objectPermission = this.listPermission.find(permisstion => permisstion.groupScreenCode === groupScreenCode);
    if (objectPermission) {
      const objectRoleAction = objectPermission.listRoleAction.find(roleAction => roleAction.screenCode === screenCode);
      // nếu bỏ check vào VIEW thì tất cả các action sau tự động bỏ check
      if (actionCode === 'VIEW' && event.target.checked === false) {
        this.listAction.forEach(action => {
          if (action.actionCode !== '' && objectRoleAction[action.actionCode]) {
            objectRoleAction[action.actionCode] = false;
          }
        });
        // neu tat VIEW cua man hình con la man hình create thì action CREATE o man hinh cha cung bo check theo
        if (this.screenIsParent(objectRoleAction.screenUrl) === false && objectRoleAction.screenUrl.indexOf('detail') < 0) {
          // tslint:disable-next-line: max-line-length
          const objScreenParent = objectPermission.listRoleAction.find(roleAction => roleAction.screenUrl === this.getUrlParent(objectRoleAction.screenUrl));
          if (objScreenParent.CREATE !== undefined) {
            objScreenParent.CREATE = false;
          }
        } else {
          // tslint:disable-next-line: max-line-length
          // const objRoleAction = objectPermission.listRoleAction.find(roleAction => roleAction.screenUrl === (objectRoleAction.screenUrl + '/create'));
          // if (objRoleAction) {
          //   objRoleAction.VIEW = false;
          //   objRoleAction.CREATE = false;
          // }
        }
        // tslint:disable-next-line: max-line-length
      } else if (actionCode === 'VIEW' && event.target.checked === true && this.screenIsParent(objectRoleAction.screenUrl) === false && objectRoleAction.screenUrl.indexOf('detail') < 0) {
        // nếu check vào view của màn hình con thì CREATE của cha sẽ sáng, CREATE của con cũng sáng theo
        // tìm screen cha
        // tslint:disable-next-line: max-line-length
        const objScreenParent = objectPermission.listRoleAction.find(roleAction => roleAction.screenUrl === this.getUrlParent(objectRoleAction.screenUrl));
        if (objScreenParent) {
          // objScreenParent.CREATE = true; // objScreenParent['CREATE'] = true
          // objScreenParent.VIEW = true;
          if (objScreenParent.VIEW !== undefined) {
            objScreenParent.VIEW = true;
          }
          if (objScreenParent.CREATE !== undefined) {
            objScreenParent.CREATE = true;
          }
        }
        if (objectRoleAction.CREATE !== undefined) {
          objectRoleAction.CREATE = true;
        }

      } else if (actionCode !== 'VIEW' && event.target.checked === true) { // nếu check vào các action khác thì tự động bật check VIEW
        objectRoleAction.VIEW = true;
        // xử lý CREATE màn hình cha thì màn hình con tự động bật VIEW và CREATE
        if (actionCode === 'CREATE' && event.target.checked === true && this.screenIsParent(objectRoleAction.screenUrl)) {
          // tslint:disable-next-line: max-line-length
          const objRoleAction = objectPermission.listRoleAction.find(roleAction => roleAction.screenUrl === (objectRoleAction.screenUrl + '/create'));
          if (objRoleAction) {
            objRoleAction.VIEW = true;
            objRoleAction.CREATE = true;
          }
        } else if (actionCode === 'CREATE' && event.target.checked === true && this.screenIsParent(objectRoleAction.screenUrl) === false) {
          // neu check vao CREATE cua man hình con
          // tslint:disable-next-line: max-line-length
          const objScreenParent = objectPermission.listRoleAction.find(roleAction => roleAction.screenUrl === this.getUrlParent(objectRoleAction.screenUrl));
          if (objScreenParent) {
            objScreenParent.CREATE = true; // objScreenParent['CREATE'] = true
            objScreenParent.VIEW = true;
          }
        }
      } else if (actionCode === 'CREATE' && event.target.checked === false) {
        if (this.screenIsParent(objectRoleAction.screenUrl)) {
          // tslint:disable-next-line: max-line-length
          const objScreenChildren = objectPermission.listRoleAction.find(roleAction => roleAction.screenUrl === (objectRoleAction.screenUrl + '/create'));
          objScreenChildren.VIEW = false;
          objScreenChildren.CREATE = false;
        } else {
          // tslint:disable-next-line: max-line-length
          const objScreenParent = objectPermission.listRoleAction.find(roleAction => roleAction.screenUrl === this.getUrlParent(objectRoleAction.screenUrl));
          objScreenParent.CREATE = false;
          objectRoleAction.VIEW = false;
        }
      }
    }
  }

  getTypeDetail(param: string, isID = false): string {
    const arrString = param.split('-');
    if (arrString.length > 1) {
      if (!isID) {
        return arrString[0];
      } else {
        return arrString[1];
      }
    } else {
      return '';
    }
  }

  screenIsParent(screenUrl: string): boolean {
    if (screenUrl.indexOf('/') > 0) {
      return false;
    }
    return true;
  }

  getUrlParent(screenUrl: string): string {
    const arrStr = screenUrl.split('/');
    if (arrStr.length > 0) {
      return arrStr[0];
    } else {
      return '';
    }
  }

  submitForm() {
    if (this.typeRole) {
      this.listRoleScreenRequest = this.getListRequest();
      this.ms017002Service.ms017002Update(this.listRoleScreenRequest).subscribe(
        res => {
          if (res.body.error === null) {
            this.toastService.show('notification-message.update-success', { classname: 'bg-success text-light', delay: 3000 });
            // this.submitted = false;
            this.router.navigate(['/permission']);
          }
        },
        (errors) => {
          if (errors) {
            this.displayErrorMessage(errors);
          }
        });
    } else {
      // TODO
    }

  }

  // set form error, translate and display message
  displayErrorMessage(errors) {
    const formControl = this.formData.get(errors.error.error.itemName);
    if (formControl) {
      this.translationService.getTranslation(errors.error.error.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }

  buttonBack() {
    this.listRoleScreenRequest = this.getListRequest();
    if (this.listRoleScreenRequest.length < 1) {
      this.router.navigate(['/permission']);
    } else {
      this.openPopup();
    }
  }

  openPopup() {
    const modalRef = this.modalService.open(ModalConfirmComponent, {centered: true});
    modalRef.componentInstance.isDelete = false;
    modalRef.componentInstance.isBack = true;
    // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
    this.translate.get('confirm-message.update').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.result.then(result => {
      if (result === 'save tab') {
        this.submitForm();
      } else {
        if (result === 'Unsave'){
          this.router.navigate(['/permission']);
        }
      }
    });
  }

  getListRequest(): RoleScreenRequest[] {
    const listRoleScreenRequest: RoleScreenRequest[] = [];
    this.listPermission.forEach(group => {
      const objRoleAction = this.listPermissionOld.find(permission => permission.groupScreenCode === group.groupScreenCode);
      if (objRoleAction) {
        group.listRoleAction.forEach(element => {
          const objAction = objRoleAction.listRoleAction.find(item => item.screenCode === element.screenCode);
          if (JSON.stringify(element) !== JSON.stringify(objAction)) {
            const listActionRequest: ActionRequest[] = [];
            this.listAction.forEach(action => {
              if (action.actionCode !== '' && element[action.actionCode] !== undefined) {
                listActionRequest.push({ actionCode: action.actionCode, access: element[action.actionCode] });
              }
            });
            // tslint:disable-next-line: max-line-length
            const roleScreenRequest: RoleScreenRequest = {
              roleCode: this.idDetail,
              screenCode: element.screenCode,
              listAction: listActionRequest
            };
            listRoleScreenRequest.push(roleScreenRequest);
          }
        });
      }
    });
    return listRoleScreenRequest;
  }
}
