import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { NgbProgressbarConfig } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { LengthConstant } from '../../../../common/constant/length';
import { DateConfig } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { Degree, Department, Employee, Nationality, PositionEmployee, StatusEmployee } from '../em001002-entity';

@Component({
  selector: 'app-em001002-personal-view',
  templateUrl: './em001002-personal-view.component.html',
  styleUrls: ['./em001002-personal-view.component.scss']
})
export class Em001002PersonalViewComponent implements OnInit, OnChanges {
  @Input() companyName: string;
  @Input() listPositionEmployee: PositionEmployee[];
  @Input() listStatusEmployee: StatusEmployee[];
  @Input() listNationality: Nationality[];
  @Input() listDepartment: Department[];
  @Input() listDegree: Degree[];
  @Input() employeeDetails: Employee;
  @Input() authButton: ScreenAction;
  @Input() isProfile: boolean;
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() isEditable: EventEmitter<boolean> = new EventEmitter();
  unsubscribe$ = new Subject();
  url = '';
  employeeName: string;
  displayMessage: string;
  errorsCode: string;
  submited: boolean;
  config = new DateConfig();
  idEmployee: number;
  isMarried = false;
  isAvatar: boolean;
  nationName: string;
  departmentName: string;
  positionName: string;
  statusName: string;
  degreeName: string;
  totalYear: number;
  totalMonth: number;
  inCompanyYear: number;
  inCompanyMonth: number;
  isHidden = true;
  constructor(
    config: NgbProgressbarConfig
  ) {
    config.max = LengthConstant.MAX_VALUE_PROGRESSBAR;
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes) {
      this.initData();
    }
  }

  ngOnInit(): void {
    // this.initData();
  }

  initData() {
    if (this.employeeDetails) {
      this.calculateExperience();
      this.employeeName = this.employeeDetails.firstName ? this.employeeDetails.firstName + ' ' : '';
      this.employeeName += this.employeeDetails.lastName ? this.employeeDetails.lastName : '';
      this.url = this.employeeDetails.avataUrl;
      this.listDepartment.forEach(element => {
        if (element.departmentID === this.employeeDetails.departmentID) {
          this.departmentName = element.departmentName;
        }
      });
      this.listNationality.forEach(element => {
        if (element.nationalityID === this.employeeDetails.nationalityID) {
          this.nationName = element.nationalityName;
        }
      });
      this.listPositionEmployee.forEach(element => {
        if (element.positionEmployeeID === this.employeeDetails.positionEmployeeID) {
          this.positionName = element.positionEmployeeName;
        }
      });
      this.listStatusEmployee.forEach(element => {
        if (element.statusEmployeeID === this.employeeDetails.statusEmployeeID) {
          this.statusName = element.statusEmployeeName;
        }
      });
      this.listDegree.forEach(element => {
        if (element.degreeID === this.employeeDetails.degreeID) {
          this.degreeName = element.degreeName;
        }
      });
    }
  }

  onEdit() {
    this.isEditable.emit(true);
  }

  openPopup() {
    this.back.emit('back');
  }

  calculateExperience() {
    this.totalYear = Math.floor(this.employeeDetails.totalExperience / 12);
    this.totalMonth = this.employeeDetails.totalExperience % 12;
    this.inCompanyYear = Math.floor(this.employeeDetails.inCompanyExperience / 12);
    this.inCompanyMonth = this.employeeDetails.inCompanyExperience % 12;
  }
}
