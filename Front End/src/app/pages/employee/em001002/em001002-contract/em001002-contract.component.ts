import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { DateConfig, formatDate, parseNewDateJs } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CustomReactiveFormValidator } from '../../../../common/validator/custom-validator';
import { DateValidator } from '../../../../common/validator/date-validator';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { Contract, ContractDetail, PositionEmployee, ServerErrors } from '../em001002-entity';
import { StoreService } from '../em001002-store-service';

@Component({
  selector: 'app-em001002-contract',
  templateUrl: './em001002-contract.component.html',
  styleUrls: ['./em001002-contract.component.scss']
})
export class Em001002ContractComponent implements OnInit, OnChanges, OnDestroy {
  @Input() listContractType: Contract[]; // dropdown data
  @Input() contract: ContractDetail;  // full information of tab's contract
  @Input() listPositionEmployee: PositionEmployee[]; // dropdown data
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() backOrCancel: EventEmitter<any> = new EventEmitter();
  @Output() contractFile = new EventEmitter<any>();

  // config datepicker
  config = new DateConfig();

  formContract: FormGroup;
  displayMessage: string;
  errorsCode: string;
  submited: boolean;
  isFileSize = false;
  base64: string;
  selectedFiles: FileList;
  file: string;
  fileName: string;
  unsubscribe$ = new Subject();
  isUpdate: boolean;
  constructor(
    private fb: FormBuilder,
    public storeService: StoreService,
    public toastService: ToastService,
  ) {
    this.file = null;
    this.formContract = this.fb.group({
      contractID: [null],
      contractTypeID: [null, Validators.required],
      positionEmployeeID: [null, Validators.required],
      contractAttachments: [null],
      contractStartDate: [formatDate(parseNewDateJs(new Date())), Validators.required],
      contractEndDate: [formatDate(parseNewDateJs(new Date())), Validators.required],
    }, { validator: Validators.compose([DateValidator.dateLessThan('contractStartDate', 'contractEndDate')]) });
  }
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes.contract.currentValue) {
      this.isUpdate = true;
      this.contract = changes.contract.currentValue;
      this.formContract.patchValue({
        contractID: changes.contract.currentValue.contractID,
      });
    } else {
      this.isUpdate = false;
    }
    this.storeService.saveValueChange(this.formContract.value);
    this.storeService.saveStore(this.formContract.value);
    // tslint:disable-next-line:max-line-length
    if (changes.contract.currentValue !== null && changes.listContractType?.currentValue !== undefined && changes.listPositionEmployee?.currentValue !== undefined) {
      this.formContract.patchValue({
        contractID: this.contract.contractID,
        contractTypeID: this.contract.contractTypeID,
        positionEmployeeID: this.contract.positionEmployeeID,
        contractAttachments: null,
        contractStartDate: formatDate(this.contract?.contractStartDate.split('T')[0]),
        contractEndDate: formatDate(this.contract?.contractEndDate.split('T')[0])
      });
    }
  }
  get f() { return this.formContract.controls; }

  ngOnInit(): void {
    // update data
    if (this.contract) {
      this.formContract.patchValue({
        contractTypeID: this.contract?.contractTypeID,
        positionEmployeeID: this.contract?.positionEmployeeID
      });
      this.contractFile.emit(this.contract?.contractAttachments);
      // create init data
    } else {
      this.formContract.patchValue({
        contractTypeID: null,
        positionEmployeeID: null
      });
    }
    // save value changes to store
    this.formContract.valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(val => {
      // update status form when submit in parent component
      this.storeService.updateStatusForm(this.formContract);
      this.storeService.saveValueChange(this.formContract.value);
    });

  }
  async submit() {
    this.submited = true;
    if (this.isUpdate === false) {
      this.f.contractTypeID.setValidators(Validators.required);
      this.f.positionEmployeeID.setValidators(Validators.required);
      this.f.contractAttachments.setValidators(Validators.required);
    }
    CustomReactiveFormValidator.updateTreeValidity(this.formContract);
    if (this.file) {
      await this.toBase64(this.file).then((val: string) => {
        this.base64 = val;
      }).catch(errors => {
        this.toastService.show('notification-message.save-fail', { classname: 'bg-danger text-light', delay: 3000 });
        return;
      });
    }
    if (this.formContract.valid && !this.isFileSize) {
      this.submitForm.emit({ ...this.formContract.value, contractAttachments: this.base64 });
      return;
    }
  }
  async selectFiles(event) {
    this.selectedFiles = event.target.files;
    const file = event.target.files[0];
    if (file !== undefined && file !== null) {
      this.fileName = file.name;
      if ((file.size / 1024) / 1024 > 25) {
        this.isFileSize = true;
        this.contractFile.emit(false);
      } else {
        this.isFileSize = false;
        await this.toBase64(file).then((val: string) => {
          this.contractFile.emit(val);
        });
      }
    }
  }
  toBase64(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }
  clickChooseFile() {
    const input = document.querySelector('input[type=file]') as HTMLElement;
    input.click();
  }
  openPopup() {
    this.back.emit('back');
  }

  canDownload(): boolean {
    if (this.contract === null || this.contract === undefined) {
      return false;
    }
    if (this.contract.contractAttachments === null
      || this.contract.contractAttachments === undefined
      || this.contract.contractAttachments.length < 1) {
      return false;
    }
    return true;
  }

  openPopupContract() {
    this.backOrCancel.emit('back');
  }
}
