import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { TranslateService } from '@ngx-translate/core';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { SortEvent, compare } from 'src/app/theme/shared/directives/sort-table-header.directive';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Listprops } from '../../../common/page-options';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { Language } from './ms005001-entity';
import { LanguageRequest } from './ms005001-request';
import { MS005001SearchService } from './ms005001.service';
import { StorageService } from '../../../services/storage/storage.service';
import { Subject } from 'rxjs';
import { Title } from '@angular/platform-browser';
import { TranslationService } from '../../../services/translate/translation.service';

@Component({
  selector: 'app-ms005001',
  templateUrl: './ms005001.component.html',
  entryComponents: [ModalConfirmComponent],
})
export class MS005001SearchComponent implements OnInit, OnDestroy {
  checked: boolean;
  backFlag: boolean;
  indeterminate = false;
  screenProps = new Listprops();
  languageOld: Language[] = [];
  bodyRequest: LanguageRequest;
  languageList: Language[];
  private unsubscribe$ = new Subject();
  formSearch: FormGroup;
  direction: string;
  column: string;
  authButton = new ScreenAction();
  // leak
  valueOld = {
    languageName: '',
    languageCode: '',
    languageCategoryID: ''
  };
  baseForm: FormGroup;

  constructor(
    private changeDetector: ChangeDetectorRef,
    private languageService: MS005001SearchService,
    private modalService: NgbModal,
    private fb: FormBuilder,
    private localStorage: StorageService,
    private toastService: ToastService,
    private commonFunctionService: CommonFunctionService,
    private translate: TranslateService,
    private titleService: Title,
    public translationService: TranslationService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formSearch = this.fb.group({
      languageName: [null],
      languageCode: [null],
      languageCategoryID: [null],
    });
    this.baseForm = this.fb.group({
      languageName: [null],
      languageCode: [null],
      languageCategoryID: [null],
    });
  }

  get f() { return this.formSearch.controls; }

  ngOnInit(): void {
    // change title
    this.translate.get('title.master.language').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.language'))
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
          languageName: data.data.languageName,
          languageCode: data.data.languageCode,
          languageCategoryID: data.data.languageCategoryID
        });
        this.initData(null, this.localStorage.getPageSize(), data.data);
      } else {
        this.formSearch = this.fb.group({
          languageName: data.data.languageName,
          languageCode: data.data.languageCode,
          languageCategoryID: data.data.languageCategoryID
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
    this.column = 'languageName';
    this.direction = 'asc';
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.direction = direction;
    this.column = column;
    this.languageList = [...this.languageList].sort((a, b) => {
      let res: number;
      let nullableStringA = '';
      let nullableStringB = '';
      switch (column) {
        case 'languageCode':
          res = compare(a.languageCode, b.languageCode);
          return direction === 'asc' ? res : -res;
        case 'languageName':
          res = compare(escape(a.languageName), escape(b.languageName));
          return direction === 'asc' ? res : -res;
        case 'languageCategoryName':
          res = compare(escape(a.languageCategoryName), escape(b.languageCategoryName));
          return direction === 'asc' ? res : -res;
        case 'languageDescription':
          nullableStringA = a.languageDescription === null ? '' : a.languageDescription;
          nullableStringB = b.languageDescription === null ? '' : b.languageDescription;
          res = escape(nullableStringA).localeCompare(escape(nullableStringB));
          return direction === 'asc' ? res : -res;
      }
    });
    // }
  }

  reset() {
    this.formSearch.reset();
    this.baseForm = this.fb.group({
      languageName: [null],
      languageCode: [null],
      languageCategoryName: [null],
    });
    this.unCheckAll();
    this.screenProps.page = 1;
    this.localStorage.removeSearchData();
    this.initData(null, this.localStorage.getPageSize(), this.baseForm.value);
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
    this.languageService.getLanguage(this.bodyRequest).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        // tells Angular that you are now ready for it to run change detection.
        // this.changeDetector.detectChanges();
        this.languageList = resp.content.data;
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
  searchLanguage() {
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    this.baseForm.value.languageName = this.valueOld.languageName;
    this.baseForm.value.languageCode = this.valueOld.languageCode;
    this.baseForm.value.languageCategoryID = this.valueOld.languageCategoryID;
    this.initData(this.screenProps.pageNumDefault, this.screenProps.pageSize, this.baseForm.value);
    this.screenProps.page = 1;
    this.unCheckAll();
    this.localStorage.saveSearchData(this.baseForm.value, null);
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
      this.initData(currentPage - 1, this.screenProps.pageSize, this.baseForm.value);
      this.localStorage.saveSearchData(null, currentPage);
    }
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }
  onItemChecked(id: number, event) {
    this.updateCheckedSet(id, event.target.checked);
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    this.checked = this.languageList.every(item => this.screenProps.setOfCheckedId.has(+item.languageID));
    this.indeterminate = this.languageList.some(item => this.screenProps.setOfCheckedId.has(+item.languageID)) && !this.checked;
  }

  deleteLanguage() {
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
        this.languageService.deleteLanguage(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
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
    this.languageList.forEach(item => {
      this.updateCheckedSet(+item.languageID, event.target.checked);
    });
  }

  unCheckAll() {
    this.checked = false;
    this.languageList.forEach(item => {
      this.screenProps.setOfCheckedId.delete(+item.languageID);
    });
  }

  trackByFn(index, item) {
    return index;
  }

}
