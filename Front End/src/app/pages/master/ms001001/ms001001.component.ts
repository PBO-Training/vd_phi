import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from '../../../services/translate/translation.service';
import { Listprops } from '../../../common/page-options';
import { StorageService } from '../../../services/storage/storage.service';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { compare, SortEvent } from '../../../theme/shared/directives/sort-table-header.directive';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { UserRequest } from './ms001001-request';
import { Role } from './ms001001-role-entity';
import { User } from './ms001001-user-entity';
import { MS001001Service } from './ms001001.service';

@Component({
  selector: 'app-ms001001',
  templateUrl: './ms001001.component.html',
  entryComponents: [ModalConfirmComponent]
})
export class MS001001Component implements OnInit, OnDestroy {
  checked: boolean;
  backFlag: boolean;
  indeterminate = false;
  screenProps = new Listprops();
  bodyRequest: UserRequest;
  userList: User[];
  userListOld: User[];
  roleList: any[] = [];
  role: Role;
  formSearch: FormGroup;
  isHidden = true;
  direction: string;
  toastTitle = 'Delete Success';
  private unsubscribe$ = new Subject();
  column: string;
  authButton = new ScreenAction();
  baseForm: FormGroup;

  valueOld = {
    searchName: '',
    searchRole: '',
  };

  constructor(
    private changeDetector: ChangeDetectorRef,
    private userService: MS001001Service,
    private modalService: NgbModal,
    private fb: FormBuilder,
    public toastService: ToastService,
    private localStorage: StorageService,
    private commonFunctionService: CommonFunctionService,
    private translate: TranslateService,
    private titleService: Title,
    public translationService: TranslationService,

  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formSearch = this.fb.group({
      searchName: [null],
      searchRole: ['-1']
    });

    this.baseForm = this.fb.group({
      searchName: [null],
      searchRole: ['-1']
    });
  }

  get f() { return this.formSearch.controls; }
  ngOnInit(): void {
    // change title
    this.translate.get('title.master.user').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.user'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    const dataSearch = this.localStorage.getSearchData();
    this.backFlag = this.localStorage.getBackFlag();
    if (this.backFlag === false && dataSearch !== null) {
      this.localStorage.removeSearchData();
    }
    if (this.backFlag === true && dataSearch !== null) {
      if (dataSearch.data === null) {
        this.initData(dataSearch.currentPage - 1, this.localStorage.getPageSize(), this.baseForm.value);
        this.screenProps.page = dataSearch.currentPage;
      } else if (dataSearch.currentPage === null) {
        this.formSearch = this.fb.group({
          searchName: dataSearch.data.searchName,
          searchRole: dataSearch.data.searchRole
        });
        this.initData(null, this.localStorage.getPageSize(), dataSearch.data);
      } else {
        this.formSearch = this.fb.group({
          searchName: dataSearch.data.searchName,
          searchRole: dataSearch.data.searchRole
        });
        this.initData(dataSearch.currentPage - 1, this.localStorage.getPageSize(), dataSearch.data);
        this.screenProps.page = dataSearch.currentPage;
      }
    } else {
      this.initData(null, this.localStorage.getPageSize(), this.baseForm.value);
    }
    this.localStorage.saveBackFlag(false);
    this.userService.initDropDownList(this.role).subscribe(val => {
      if (val.content.listRole) {
        this.roleList = val.content.listRole;
      }
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  resetSort() {
    // reset sort
    this.column = 'username';
    this.direction = 'asc';
  }

  reset() {
    // this.formSearch.reset();
    this.formSearch = this.fb.group({
      searchName: [null],
      searchRole: ['-1']
    });
    this.baseForm = this.fb.group({
      searchName: [null],
      searchRole: ['-1']
    });
    this.unCheckAll();
    this.screenProps.page = 1;
    this.initData(null, this.localStorage.getPageSize(), this.formSearch.value);
    this.localStorage.removeSearchData();
  }

  private initData(pageN: any, pSize: any, formSearch: any): void {
    this.resetSort();
    this.bodyRequest = {
      pageSize: pSize,
      pageNum: pageN,
      ...formSearch
    };
    this.userService.getUser(this.bodyRequest).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        // tells Angular that you are now ready for it to run change detection.
        // this.changeDetector.detectChanges();
        this.userList = resp.content.data;
        this.userListOld = resp.content.data;
        this.screenProps.pageNum = resp.content.pageNum ? resp.content.pageNum : 0;
        this.screenProps.pageSize = resp.content.pageSize ? resp.content.pageSize : 30;
        this.screenProps.totalRecord = resp.content.totalElements;
      }
    });
  }
  checkAll(event) {
    this.userList.forEach(item => {
      this.updateCheckedSet(+item.userID, event.target.checked);
      this.refreshCheckedStatus();
    });
  }
  searchUser() {
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    this.baseForm.value.searchName = this.valueOld.searchName;
    this.baseForm.value.searchRole = this.valueOld.searchRole;
    this.initData(this.screenProps.pageNumDefault, this.screenProps.pageSize, this.baseForm.value);
    this.screenProps.page = 1;
    this.unCheckAll();
    this.localStorage.saveSearchData(this.baseForm.value, null);
  }

  changePageSize(pageSize: number): void {
    this.unCheckAll();
    this.screenProps.pageSize = pageSize;
    this.localStorage.savePageSizeLocal(this.screenProps.pageSize);
    const dataSearch = this.localStorage.getSearchData();
    if (dataSearch !== null) {
      if (dataSearch.currentPage === null) {
        this.initData(null, pageSize, dataSearch.data);
      } else if (dataSearch.data === null) {
        this.initData(dataSearch.currentPage - 1, pageSize, this.baseForm.value);
      } else {
        this.initData(dataSearch.currentPage - 1, pageSize, dataSearch.data);
      }
    } else {
      this.initData(null, pageSize, this.baseForm.value);
    }
  }

  pageChangeOutput(currentPage: number) {
    this.unCheckAll();
    const dataSearch = this.localStorage.getSearchData();
    if (dataSearch !== null && dataSearch.data !== null) {
      this.initData(currentPage - 1, this.screenProps.pageSize, dataSearch.data);
      this.localStorage.saveSearchData(dataSearch.data, currentPage);
    } else {
      this.initData(currentPage - 1, this.screenProps.pageSize, this.baseForm.value);
      this.localStorage.saveSearchData(null, currentPage);
    }
  }

  onItemChecked(id: number, event) {
    this.updateCheckedSet(id, event.target.checked);
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.userList.every(item => this.screenProps.setOfCheckedId.has(+item.userID));
    this.indeterminate = this.userList.some(item => this.screenProps.setOfCheckedId.has(+item.userID)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }

  deleteUser() {
    if (this.screenProps.setOfCheckedId.size > 0) {
      this.screenProps.setOfCheckedId.forEach(x => {
        this.screenProps.ids.push(x);
      });
      this.openPopup(this.screenProps.ids);
    } else {
      this.toastService.show('notification-message.unchecked', { classname: 'bg-warning text-light', delay: 3000 });
    }
  }
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
        this.userService.deleteListUser(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
          if (resp.content) {
            // show toast if delete success
            this.screenProps.ids = [];
            this.screenProps.setOfCheckedId.clear();
            this.checked = false;
            this.initData(null, this.localStorage.getPageSize(), this.formSearch.value);
            this.screenProps.page = 1;
            this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
          } else if (resp.error) {
            // show toast if delete fail
            this.toastService.show('notification-message.delete-fail', { classname: 'bg-success text-light', delay: 3000 });
          }
        });
      }
    }, reason => {
    });
  }

  trackByFn(index, item) {
    return index;
  }

  unCheckAll() {
    this.checked = false;
    this.userList.forEach(item => {
      this.screenProps.setOfCheckedId.delete(+item.userID);
    });
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.direction = direction;
    this.column = column;
    this.userList = [...this.userList].sort((a, b) => {
      let res: number;
      let x: string;
      let y: string;
      switch (column) {
        case 'username':
          res = compare(a[column], b[column]);
          return direction === 'asc' ? res : -res;
        case 'role.roleName':
          x = a.role == null ? '' : a.role.roleName;
          y = b.role == null ? '' : b.role.roleName;
          res = x.localeCompare(y);
          return direction === 'asc' ? res : -res;
        case 'employeeName':
          res = compare(a[column], b[column]);
          return direction === 'asc' ? res : -res;
      }
    });
  }
}
