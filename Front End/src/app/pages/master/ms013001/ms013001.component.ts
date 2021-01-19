import { mergeMap, takeUntil } from 'rxjs/operators';
import { SortEvent, compare } from './../../../theme/shared/directives/sort-table-header.directive';
import { TranslateService } from '@ngx-translate/core';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { ToastService } from './../../../theme/shared/components/toast-container/toast-service';
import { StorageService } from './../../../services/storage/storage.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Subject } from 'rxjs';
import { HolidayRequest } from './ms013001-request';
import { Holiday } from './ms013001-entity';
import { Listprops } from './../../../common/page-options';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { MS013001SearchService } from './ms013001.service';
import { Title } from '@angular/platform-browser';
import { TranslationService } from '../../../services/translate/translation.service';

@Component({
  selector: 'app-ms013001',
  templateUrl: './ms013001.component.html',
  entryComponents: [ModalConfirmComponent],
})
export class MS013001SearchComponent implements OnInit, OnDestroy {
  checked: boolean;
  backFlag: boolean;
  indeterminate = false;
  screenProps = new Listprops();
  holidayOld: Holiday[] = [];
  bodyRequest: HolidayRequest;
  holidayList: Holiday[];
  private unsubscribe$ = new Subject();
  formSearch: FormGroup;
  direction: string;
  column: string;
  authButton = new ScreenAction();
  valueOld = {
    holidayName: '',
    holidayYear: '',
  };
  baseForm: FormGroup;

  constructor(
    private changeDetector: ChangeDetectorRef,
    private holidayService: MS013001SearchService,
    private modalService: NgbModal,
    private fb: FormBuilder,
    private localStorage: StorageService,
    // import toast in common components toast service
    public toastService: ToastService,
    public commonFunctionService: CommonFunctionService,
    private translate: TranslateService,
    private titleService: Title,
    public translationService: TranslationService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formSearch = this.fb.group({
      holidayName: [null],
      holidayYear: [null]
    });
    this.baseForm = this.fb.group({
      holidayName: [null],
      holidayYear: [null]
    });
  }

  get f() { return this.formSearch.controls; }

  ngOnInit(): void {
    // change title
    this.translate.get('title.master.holiday').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.holiday'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

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
          holidayName: data.data.holidayName,
          holidayYear: data.data.holidayYear
        });
        this.initData(null, this.localStorage.getPageSize(), data.data);
      } else {
        this.formSearch = this.fb.group({
          holidayName: data.data.holidayName,
          holidayYear: data.data.holidayYear
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

  resetSort() {
    // reset sort
    this.column = 'holidayName';
    this.direction = 'asc';
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.direction = direction;
    this.column = column;
    this.holidayList = [...this.holidayList].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
    // }
  }

  reset() {
    this.formSearch.reset();
    this.baseForm = this.fb.group({
      holidayName: [null],
      holidayYear: [null]
    });
    this.unCheckAll();
    this.localStorage.removeSearchData();
    this.initData(null, this.localStorage.getPageSize(), this.baseForm.value);
    this.screenProps.page = 1;
  }

  private initData(pageN: any, pSize: any, formSearch: any): void {
    this.screenProps.loading = true;
    this.resetSort();
    // trigger spiner
    this.bodyRequest = {
      pageSize: pSize,
      pageNum: pageN,
      ...formSearch
    };
    this.holidayService.getHoliday(this.bodyRequest).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        // tells Angular that you are now ready for it to run change detection.
        // this.changeDetector.detectChanges();
        this.holidayList = resp.content.data;
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

  searchHoliday() {
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    this.baseForm.value.holidayName = this.valueOld.holidayName;
    this.baseForm.value.holidayYear = this.valueOld.holidayYear;
    this.initData(this.screenProps.pageNumDefault, this.screenProps.pageSize, this.baseForm.value);
    this.localStorage.saveSearchData(this.baseForm.value, null);
    this.screenProps.page = 1;
    this.unCheckAll();
  }

  changePageSize(pageSize: number): void {
    this.unCheckAll();
    this.screenProps.pageSize = pageSize;
    // save pageSize
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

  onItemChecked(id: number, event) {
    this.updateCheckedSet(id, event.target.checked);
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.holidayList.every(item => this.screenProps.setOfCheckedId.has(+item.holidayID));
    this.indeterminate = this.holidayList.some(item => this.screenProps.setOfCheckedId.has(+item.holidayID)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }

  deleteHoliday() {
    if (this.screenProps.setOfCheckedId.size > 0) {
      this.screenProps.setOfCheckedId.forEach(x => {
        this.screenProps.ids.push(x);
      });
      this.openPopup(this.screenProps.ids);
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
        this.holidayService.deleteHoliday(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
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

  checkAll(event) {
    this.holidayList.forEach(item => {
      this.updateCheckedSet(+item.holidayID, event.target.checked);
    });
  }

  unCheckAll() {
    this.checked = false;
    this.holidayList?.forEach(item => {
      this.screenProps.setOfCheckedId.delete(+item.holidayID);
    });
  }

  trackByFn(index, item) {
    return index;
  }

}
