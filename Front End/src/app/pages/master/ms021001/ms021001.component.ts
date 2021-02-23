import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { Listprops } from 'src/app/common/page-options';
import { ScreenAction } from 'src/app/common/screen-action/screen-action';
import { TranslationService } from 'src/app/services/translate/translation.service';
import { ModalConfirmComponent } from 'src/app/theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from 'src/app/theme/shared/components/toast-container/toast-service';
import { compare } from 'src/app/theme/shared/directives/sort-table-header.directive';
import { ShiftWorkMaster, ShiftWorkRequest } from './ms021001.i';
import { MS021001Service } from './ms021001.service';

@Component({
  selector: 'app-ms021001',
  templateUrl: './ms021001.component.html'
})
export class MS021001Component implements OnInit {
  checked: boolean;
  direction: string;
  column: string;
  screenProps = new Listprops();
  formSearch: FormGroup;
  authButton = new ScreenAction();
  shiftWorkList : ShiftWorkMaster[];
  boxShiftWorkCode : ShiftWorkMaster[];
  getAllRequest : ShiftWorkRequest ;
  deleteRequest : any;
  private unsubscribe$ = new Subject();
  valueOld = {
    shiftWorkOptionName: '',
    shiftWorkOptionCode: '',
  };
  baseForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private shiftWorkService : MS021001Service,
    private titleService: Title,
    private translate: TranslateService,
    private modalService: NgbModal,
    private toastService: ToastService,
    public translationService: TranslationService,
    ) {
    this.formSearch = this.fb.group({
      shiftWorkOptionCode : [null],
      shiftWorkOptionName : [null]
    })
    this.baseForm = this.fb.group({
      shiftWorkOptionCode : [null],
      shiftWorkOptionName : [null]
    })
   }

  ngOnInit(): void {
    // change title
    this.translate.get('title.master.shift-work').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.master.shift-work'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    this.initData(null,30,this.baseForm.value);
    this.initBoxCode();
  }
  initBoxCode(){
    let Request={
      pageNum: "",
      pageSize: ""
    }
    this.shiftWorkService.getAllShifwork(Request).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        this.boxShiftWorkCode = resp.content.data;
      }
    })
  }
  initData(pNum : any,pSize: any,formSearch: any){
    this.getAllRequest = {
      pageNum:pNum,
      pageSize:pSize,
      ...formSearch
    }
    this.shiftWorkService.getAllShifwork(this.getAllRequest).subscribe(resp => {
      if (resp.content.data !== undefined && resp.content.data !== null && resp.content.data.length >= 0) {
        this.shiftWorkList = resp.content.data;
        this.screenProps.pageNum = resp.content.pageNum ? resp.content.pageNum : 0;
        this.screenProps.pageSize = resp.content.pageSize ? resp.content.pageSize : 30;
        this.screenProps.totalRecord = resp.content.totalElements;

      }
    });
  }
  searchShiftwork(){
    this.screenProps.page =1 ;
    this.valueOld = JSON.parse(JSON.stringify(this.formSearch.value));
    this.baseForm.value.shiftWorkOptionCode = this.valueOld.shiftWorkOptionCode;
    this.baseForm.value.shiftWorkOptionName = this.valueOld.shiftWorkOptionName;
    this.initData(null, this.screenProps.pageSize, this.baseForm.value);

  }
  resetSort() {
    // reset sort
    this.column = 'shiftworkName';
    this.direction = 'asc';
  }
  reset(){
    this.screenProps.page =1 ;
    this.formSearch.reset();
    this.resetSort();
    this.baseForm = this.fb.group({
      shiftWorkOptionCode : [null],
      shiftWorkOptionName : [null]
    });
    this.initData(null, this.screenProps.pageSize, this.baseForm.value);

  }
  deleteShilftwork(){
      this.screenProps.ids =[];
      if(this.screenProps.setOfCheckedId.size > 0){
          this.screenProps.setOfCheckedId.forEach(x => {
            this.screenProps.ids.push(x);
        })
        this.openPopup(this.screenProps.ids)
      }else {
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
      this.deleteRequest={"listDelete": ids}
      if (result === 'delete') {
         this.shiftWorkService.deleteShiftWork(this.deleteRequest).subscribe(resp =>{
          if(resp.content > 0){
            this.checked = false;
            this.screenProps.ids = [];
            this.screenProps.setOfCheckedId.clear();
            this.reset();
            this.screenProps.page = 1;
            this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
          }
         })
      }

    });
  }
  changePageSize(pageSize: number): void {
    this.checked = false;
    this.checkAll(false);
    this.screenProps.pageSize = pageSize;
    this.initData(null, pageSize, this.baseForm.value);

  }
  pageChangeOutput(currentPage: number) {
    this.checked = false;
    this.checkAll(false);
    this.screenProps.pageNum = currentPage - 1;
    this.initData(this.screenProps.pageNum, this.screenProps.pageSize, this.baseForm.value);

  }
  updateCheckedSet(id: number, checked: boolean): void{
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }
  checkAll(checked){
    this.shiftWorkList.forEach(item => {
    this.updateCheckedSet(item.shiftWorkOptionID, checked);
    });
  }
  onItemChecked(id: number, event){
    this.updateCheckedSet(id,event.target.checked);
    if(this.screenProps.setOfCheckedId.size == this.shiftWorkList.length){
      this.checked = true;
    }else {
      this.checked = false;
    }
  }
  onSort(event){
    this.direction = event.direction;
    this.column = event.column;
    this.shiftWorkList = [...this.shiftWorkList].sort((a, b) => {
      let res: number;
      switch (event.column) {
        case 'shiftworkCode':
          res = compare(a.shiftWorkOptionCode, b.shiftWorkOptionCode);
          return event.direction === 'asc' ? res : -res;
        case 'shiftworkName':
          res = compare(a.shiftWorkOptionName, b.shiftWorkOptionName);
          return event.direction === 'asc' ? res : -res;
        case 'shiftWorkTime':
          res = compare(a.shiftWorkOptionTime, b.shiftWorkOptionTime);
          return event.direction === 'asc' ? res : -res;
        case 'shiftworkDescription':
          res = compare(a.shiftWorkOptionDescription, b.shiftWorkOptionDescription);
          return event.direction === 'asc' ? res : -res;
      }
    })
  };

}
