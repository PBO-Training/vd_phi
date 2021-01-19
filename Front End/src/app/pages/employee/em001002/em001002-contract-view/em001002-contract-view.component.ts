import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Observable } from 'rxjs';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { Contract, ContractDetail, PositionEmployee, ServerErrors } from '../em001002-entity';

@Component({
  selector: 'app-em001002-contract-view',
  templateUrl: './em001002-contract-view.component.html',
  styleUrls: ['./em001002-contract-view.component.scss']
})
export class Em001002ContractViewComponent implements OnInit, OnChanges {
  @Input() listContractType: Contract[]; // dropdown data
  @Input() contract: ContractDetail;  // full information of tab's contract
  @Input() listPositionEmployee: PositionEmployee[]; // dropdown data
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() isEditable: EventEmitter<boolean> = new EventEmitter();
  @Output() addNewContract: EventEmitter<boolean> = new EventEmitter();

  contractTypeName: string;
  positionName: string;
  startDate: string;
  endDate: string;
  constructor(
    public toastService: ToastService
  ) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.contract.currentValue) {
      this.contract = changes.contract.currentValue;
      this.contractTypeName = this.getContractTypeName(changes.contract.currentValue.contractTypeID);
      this.positionName = this.getPositionName(changes.contract.currentValue.positionEmployeeID);
      this.startDate = changes.contract.currentValue.contractStartDate;
      this.endDate = changes.contract.currentValue.contractEndDate;
    }
  }

  ngOnInit(): void {
  }

  onEditContract() {
    this.isEditable.emit(true);
  }

  openPopup() {
    this.back.emit('back');
  }

  getContractTypeName(contractTypeID: number): string {
    let result = '';
    this.listContractType.forEach(type => {
      if (type.contractTypeID === contractTypeID) {
        result = type.contractTypeName;
      }
    });
    return result;
  }

  getPositionName(positionEmployeeID: number): string {
    let result = '';
    this.listPositionEmployee.forEach(position => {
      if (position.positionEmployeeID === positionEmployeeID) {
        result = position.positionEmployeeName;
      }
    });
    return result;
  }

  isContractExisted(): boolean {
    if (this.contract === null || this.contract === undefined) {
      return false;
    }
    return true;
  }

  canDownload(): boolean {
    if (!this.isContractExisted()) {
      return false;
    }
    if (this.contract.contractAttachments === null
      || this.contract.contractAttachments === undefined
      || this.contract.contractAttachments.length < 1) {
      return false;
    }
    return true;
  }

  downloadAttachment() {
    if (this.contract.contractAttachments === null
      || this.contract.contractAttachments === undefined
      || this.contract.contractAttachments.length < 1) {
      this.toastService.show('notification-message.download-fail', { classname: 'bg-danger text-light', delay: 3000 });
      return;
    }
    const a = document.createElement('a'); // Create <a>
    a.href = this.contract.contractAttachments; // Base64 Goes here
    a.download = new Date().valueOf().toString(); // File name Here
    a.click(); // Downloaded file
  }

  addContract() {
    this.addNewContract.emit(true);
  }
}
