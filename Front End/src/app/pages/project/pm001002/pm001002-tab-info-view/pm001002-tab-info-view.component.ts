import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
// import { ScreenAction } from 'src/app/common/screen-action/screen-action';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { DropDownDataProjectInfo, Pm001002Response } from '../pm001002-entity';

@Component({
  selector: 'app-project-tab-info-view',
  templateUrl: './pm001002-tab-info-view.component.html',
  styleUrls: ['./pm001002-tab-info-view.component.scss']
})
export class Pm001002TabInfoViewComponent implements OnInit, OnChanges {
  @Output() isBack = new EventEmitter<boolean>();
  @Output() isEdit = new EventEmitter<boolean>();
  @Input() dropdownData: DropDownDataProjectInfo;
  @Input() projectResponse: Pm001002Response;
  authButton = new ScreenAction();

  constructor(
    public common: CommonFunctionService,
  ) {
    this.authButton = this.common.initAuthAction(this.authButton);
  }
  ngOnChanges(changes: SimpleChanges): void {
  }

  ngOnInit(): void {

  }

  onEdit(): void {
    this.isEdit.emit(true);
  }
}
