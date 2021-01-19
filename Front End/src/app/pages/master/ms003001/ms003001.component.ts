import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { TranslationService } from '../../../services/translate/translation.service';
import { collapse, rotate } from '../../../common/animation/animation-search-field';
import { Listprops } from '../../../common/page-options';
import { ModalConfirmComponent } from '../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from '../../../theme/shared/components/toast-container/toast-service';
import { compare, SortEvent, SortTableDirective } from '../../../theme/shared/directives/sort-table-header.directive';
import { ScreenAction } from './../../../common/screen-action/screen-action';
import { CommonFunctionService } from './../../../services/common-function/common-function.service';
import { StorageService } from './../../../services/storage/storage.service';
import { Skill } from './ms003001-entity';
import { SkillRequest } from './ms003001-request';
import { MS003001Service } from './ms003001.service';

@Component({
  selector: 'app-skill-list',
  templateUrl: './ms003001.component.html',
  // changeDetection: ChangeDetectionStrategy.OnPush
  entryComponents: [ModalConfirmComponent],
  animations: [
    collapse,
    rotate
  ]
})
export class MS003001Component implements OnInit, OnDestroy {
  // using viewchildren and query list => list sort table
  @ViewChildren(SortTableDirective) headers: QueryList<SortTableDirective>;
  checked: boolean;
  backFlag: boolean;
  indeterminate = false;
  screenProps = new Listprops();
  skillOld: Skill[] = [];
  bodyRequest: SkillRequest;
  skillList: Skill[];
  type: any[] = [];
  private unsubscribe$ = new Subject();
  formSearch: FormGroup;
  direction: string;
  column: string;
  authButton = new ScreenAction();
  // leak
  valueOld = {
    skillName: '',
    skillCode: '',
    skillTypeID: '',
  };
  baseForm: FormGroup;

  constructor(
    private changeDetector: ChangeDetectorRef,
    private skillService: MS003001Service,
    private modalService: NgbModal,
    private fb: FormBuilder,
    private localStorage: StorageService,
    private titleService: Title,
    // import toast in common components toast service
    public toastService: ToastService,
    private commonFunctionService: CommonFunctionService,
    private translate: TranslateService,
    public translationService: TranslationService,
  ) {
    this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    this.formSearch = this.fb.group({
      skillName: [null],
      skillCode: [null],
      skillTypeID: ['-1']
    });
    this.baseForm = this.fb.group({
      skillName: [null],
      skillCode: [null],
      skillTypeID: ['-1']
    });
  }
  get f() { return this.formSearch.controls; }

  ngOnInit(): void {
    // change title
    this.translate.get('title.master.skill').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.skill'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

    // default get all with params pageNum = 1 , pageSize = 30, search = ""
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
          skillName: dataSearch.data.skillName,
          skillCode: dataSearch.data.skillCode,
          skillTypeID: dataSearch.data.skillTypeID
        });
        this.initData(null, this.localStorage.getPageSize(), dataSearch.data);
      } else {
        this.formSearch = this.fb.group({
          skillName: dataSearch.data.skillName,
          skillCode: dataSearch.data.skillCode,
          skillTypeID: dataSearch.data.skillTypeID
        });
        this.initData(dataSearch.currentPage - 1, this.localStorage.getPageSize(), dataSearch.data);
        this.screenProps.page = dataSearch.currentPage;
      }
    } else {
      this.initData(null, this.localStorage.getPageSize(), this.baseForm.value);
    }
    this.localStorage.saveBackFlag(false);
    // sort
    this.skillService.ms0030001Init().subscribe(
      data => {
        if (data.content) {
          this.type = data.content.listSkillType;
        }
      }
    );
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  resetSort() {
    // reset sort
    this.column = 'skillName';
    this.direction = 'asc';
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.direction = direction;
    this.column = column;
    this.skillList = [...this.skillList].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }
  reset() {
    this.formSearch = this.fb.group({
      skillName: [null],
      skillCode: [null],
      skillTypeID: ['-1']
    });
    this.baseForm = this.fb.group({
      skillName: [null],
      skillCode: [null],
      skillTypeID: ['-1']
    });
    this.unCheckAll();
    this.initData(null, this.localStorage.getPageSize(), this.formSearch.value);
    this.screenProps.page = 1;
    this.localStorage.removeSearchData();
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
    this.skillService.getSkill(this.bodyRequest).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        // tells Angular that you are now ready for it to run change detection.
        // this.changeDetector.detectChanges();
        this.skillList = resp.content.data;
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
  searchSkill() {
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    this.baseForm.value.skillName = this.valueOld.skillName;
    this.baseForm.value.skillCode = this.valueOld.skillCode;
    this.baseForm.value.skillTypeID = this.valueOld.skillTypeID;
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
    this.checked = this.skillList.every(item => this.screenProps.setOfCheckedId.has(+item.skillID));
    this.indeterminate = this.skillList.some(item => this.screenProps.setOfCheckedId.has(+item.skillID)) && !this.checked;
  }

  updateCheckedSet(id: number, checked: boolean): void {
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }

  deleteSkill() {
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
        this.skillService.deleteSkill(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(resp => {
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
    this.skillList.forEach(item => {
      this.updateCheckedSet(+item.skillID, event.target.checked);
      this.refreshCheckedStatus();
    });
  }

  unCheckAll() {
    this.checked = false;
    this.skillList.forEach(item => {
      this.screenProps.setOfCheckedId.delete(+item.skillID);
    });
  }

  trackByFn(index, item) {
    return index;
  }
}
