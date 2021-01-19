import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { SortEvent, compare } from './../../../theme/shared/directives/sort-table-header.directive';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { Listprops } from '../../../common/page-options';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { StorageService } from './../../../services/storage/storage.service';
import { Customer } from './ms009001-entity';
import { CustomerRequest } from './ms009001-request';
import { MS009001SearchService } from './ms009001.service';
import { TranslationService } from '../../../services/translate/translation.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-customer-list',
  templateUrl: './ms009001.component.html',
  entryComponents: [ModalConfirmComponent]
})
export class MS009001SearchComponent implements OnInit, OnDestroy {
  formSearch: FormGroup;
  backFlag: boolean;
  customerOld: Customer[] = [];
  checked: boolean;
  indeterminate = false;
  private unsubscribe$ = new Subject();
  // List customerID is choose
  screenProps = new Listprops();
  // Modal contain property request to server
  bodyRequest: CustomerRequest;
  // Modal contain property receive data from server
  customerList: Customer[];
  // Something
  isHidden = true;
  direction: string;
  column: string;
  authButton = new ScreenAction();
  valueOld = {
    customerName: '',
    customerCode: '',
  };
  baseForm: FormGroup;

  constructor(
    private changeDetector: ChangeDetectorRef,
    private customerService: MS009001SearchService,
    private modalService: NgbModal,
    private fb: FormBuilder,
    private toast: ToastService,
    private localStorage: StorageService,
    private commonFunctionService: CommonFunctionService,
    private translate: TranslateService,
    private titleService: Title,
    public translationService: TranslationService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formSearch = this.fb.group({
      customerName: [null],
      customerCode: [null]
    });
    this.baseForm = this.fb.group({
      customerName: [null],
      customerCode: [null]
    });
  }
  get f() { return this.formSearch.controls; }
  ngOnInit(): void {
    // change title
    this.translate.get('title.master.customer').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.customer'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // default get all with params pageNum = 1 , pageSize = 30, search = ""
    const data = this.localStorage.getSearchData();
    this.backFlag = this.localStorage.getBackFlag();
    if (this.backFlag === false && data !== null) {
      this.localStorage.removeSearchData();
    }
    if (this.backFlag === true && data !== null) {
      if (data.data === null) {
        this.initData(data.currentPage - 1, this.localStorage.getPageSize(), this.baseForm.value);
        this.screenProps.page = data.currentPage;
      } else if (data.currentPage === null) {
        this.formSearch = this.fb.group({
          customerName: data.data.customerName,
          customerCode: data.data.customerCode
        });
        this.initData(null, this.localStorage.getPageSize(), data.data);
      } else {
        this.formSearch = this.fb.group({
          customerName: data.data.customerName,
          customerCode: data.data.customerCode
        });
        this.initData(data.currentPage - 1, this.localStorage.getPageSize(), data.data);
        this.screenProps.page = data.currentPage;
      }
    } else {
      this.initData(null, this.localStorage.getPageSize(), this.baseForm.value);
    }
    this.localStorage.saveBackFlag(false);
  }
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  // Clear text in form search
  reset() {
    this.formSearch.reset();
    this.baseForm = this.fb.group({
      customerName: [null],
      customerCode: [null]
    });
    this.unCheckAll();
    this.screenProps.page = 1;
    this.localStorage.removeSearchData();
    this.initData(null, this.localStorage.getPageSize(), this.baseForm.value);
  }

  resetSort() {
    // reset sort
    this.column = 'customerName';
    this.direction = 'asc';
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.direction = direction;
    this.column = column;
    this.customerList = [...this.customerList].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }

  // Init data when navigate customer screen
  private initData(pageN: any, pSize: any, formSearch: any): void {
    this.screenProps.loading = true;
    this.resetSort();
    // trigger spiner
    this.bodyRequest = {
      pageSize: pSize,
      pageNum: pageN,
      ...formSearch
    };
    this.customerService.getCustomer(this.bodyRequest).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        this.customerList = resp.content.data;
        this.screenProps.pageNum = resp.content.pageNum ? resp.content.pageNum : 0;
        this.screenProps.pageSize = resp.content.pageSize ? resp.content.pageSize : 30;
        this.screenProps.totalRecord = resp.content.totalElements;
        this.screenProps.loading = false;
      }
    },
      err => {
        if (err.message) {
          this.screenProps.loading = false;
        }
      });
  }

  // Check formSearch is init then call API
  searchCustomer() {
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    this.baseForm.value.customerName = this.valueOld.customerName;
    this.baseForm.value.customerCode = this.valueOld.customerCode;
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
      this.localStorage.saveSearchData(dataSearch.data, currentPage);
      this.initData(currentPage - 1, this.screenProps.pageSize, dataSearch.data);
    } else {
      this.localStorage.saveSearchData(null, currentPage);
      this.initData(currentPage - 1, this.screenProps.pageSize, this.baseForm.value);
    }
  }
  // active when choose customer
  onItemChecked(id: number, event) {
    this.updateCheckedSet(id, event.target.checked);
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.customerList.every(item => this.screenProps.setOfCheckedId.has(+item.customerID));
    this.indeterminate = this.customerList.some(item => this.screenProps.setOfCheckedId.has(+item.customerID)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }

  // active when click button delete
  deleteCustomer() {
    if (this.screenProps.setOfCheckedId.size > 0) {
      this.screenProps.setOfCheckedId.forEach(x => {
        if (x) {
          this.screenProps.ids.push(x);
        }
      });
      this.openPopup(this.screenProps.ids);
    } else {
      this.toast.show('notification-message.unchecked', { classname: 'bg-warning text-light', delay: 3000 });
    }
  }
  // Logic open dialog
  openPopup(ids: number[]) {
    const modalRef = this.modalService.open(ModalConfirmComponent, {centered: true});
    this.translate.get('confirm-message.delete').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.result.then(result => {
      // If delete success then result = delete
      if (result === 'delete') {
        this.customerService.deleteCustomer(ids).subscribe(resp => {
          if (resp.error === null) {
            this.isHidden = false;
            this.screenProps.ids = [];
            this.screenProps.setOfCheckedId.clear();
            this.checked = false;
            // Call back api search customer and rerender
            this.initData(null, this.localStorage.getPageSize(), this.formSearch.value);
            this.screenProps.page = 1;
            this.toast.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
          } else if (resp.error) {
            // show toast if delete fail
            this.toast.show('notification-message.delete-fail', { classname: 'bg-success text-light', delay: 3000 });
          }
        });
      }
    }, reason => {
    });
  }

  checkAll(event) {
    this.customerList.forEach(item => {
      this.updateCheckedSet(+item.customerID, event.target.checked);
    });
  }

  unCheckAll() {
    this.checked = false;
    this.customerList.forEach(item => {
      this.screenProps.setOfCheckedId.delete(+item.customerID);
    });
  }

  // Hidden dialog
  trackByFn(index, item) {
    return index;
  }
}
