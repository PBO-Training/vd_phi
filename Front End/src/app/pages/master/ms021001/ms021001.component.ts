import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Listprops } from 'src/app/common/page-options';
import { ScreenAction } from 'src/app/common/screen-action/screen-action';
import { compare } from 'src/app/theme/shared/directives/sort-table-header.directive';
import { ShiftWorkMaster } from './ms021001-entity';
import { ShiftWorkRequest } from './ms021001-request';
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
  getAllRequest : ShiftWorkRequest ;

  constructor(
    private fb: FormBuilder,
    private shiftWorkService : MS021001Service
    ) {
    this.formSearch = this.fb.group({
      shiftWorkOptionCode : [null],
      shiftWorkOptionName : [null]
    })
   }

  ngOnInit(): void {
    this.searchShiftwork(null,30);
  }
  searchShiftwork(pNum : any,pSize: any){
    this.getAllRequest = {
      pageNum:pNum,
      pageSize:pSize,
      ...this.formSearch.value
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
  resetSort() {
    // reset sort
    this.column = 'shiftworkName';
    this.direction = 'asc';
  }
  reset(){
    this.formSearch.reset();
    this.resetSort();
    this.searchShiftwork(null,this.screenProps.pageSize);
  }
  deleteShilftwork(){

  }
  changePageSize(pageSize: number): void {
    this.screenProps.pageSize = pageSize;
    this.searchShiftwork(null,this.screenProps.pageSize);

  }
  pageChangeOutput(currentPage: number) {
    this.screenProps.pageNum = currentPage - 1;
    this.searchShiftwork(this.screenProps.pageNum,this.screenProps.pageSize);

  }
  updateCheckedSet(id: number, checked: boolean): void{
    if (checked) {
      this.screenProps.setOfCheckedId.add(id);
    } else {
      this.screenProps.setOfCheckedId.delete(id);
    }
  }
  checkAll(event){
    this.shiftWorkList.forEach(item => {
    this.updateCheckedSet(+item.shiftWorkOptionID, event.target.checked);
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
        case 'shiftWorkTimeAM':
          res = compare(a.shiftWorkOptionStartTimeAM, b.shiftWorkOptionStartTimeAM);
          return event.direction === 'asc' ? res : -res;
        case 'shiftWorkTimePM':
          res = compare(a.shiftWorkOptionStartTimePM, b.shiftWorkOptionEndTimePM);
          return event.direction === 'asc' ? res : -res;
        case 'shiftworkDescription':
          res = compare(a.shiftWorkOptionDescription, b.shiftWorkOptionDescription);
          return event.direction === 'asc' ? res : -res;
      }
    })
  };
  trackByFn(index, item) {
    return index;
  }
}
