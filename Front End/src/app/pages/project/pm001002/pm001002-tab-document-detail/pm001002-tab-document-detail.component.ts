import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { LengthConstant } from '../../../../common/constant/length';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { Pm001002Service } from '../pm001002.service';

@Component({
  selector: 'app-pm001002-tab-document-detail',
  templateUrl: './pm001002-tab-document-detail.component.html',
  styleUrls: ['./pm001002-tab-document-detail.component.scss']
})
export class Pm001002TabDocumentDetailComponent implements OnInit {
  @Output() navigateToDocument = new EventEmitter<boolean>();
  @Output() ReGetListDocument = new EventEmitter<boolean>();
  formDetails: FormGroup;
  private unsubscribe$ = new Subject();
  submited = false;
  errorsCode: string;
  displayMessage: string;
  selectedFiles: FileList;
  currentFileUpload: File;
  fileExceedData: boolean;
  MEGABYTE = 1024 * 1024;

  constructor(
    private projectSerivce: Pm001002Service,
    private router: ActivatedRoute,
    private fb: FormBuilder,
    public toastService: ToastService,
    private translateService: TranslateService,
  ) {
    this.initForm();
  }

  ngOnInit(): void {
  }

  get f() { return this.formDetails.controls; }

  selectFile(event) {
    this.fileExceedData = false;
    this.selectedFiles = event.target.files;
    if (this.selectedFiles.length === 0) {
      this.fileExceedData = false;
      this.selectedFiles = undefined;
    } else {
      const fileSelected = this.selectedFiles.item(0);
      if (this.bytesToMegaBytes(fileSelected.size) > 25) {
        this.fileExceedData = true;
      }
    }
  }

  initForm = () => {
    this.formDetails = this.fb.group({
      file: [null, [Validators.required]],
      projectID: [this.router.snapshot.params.id],
      description: ['', [Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]],
    });
  }

  saveData = () => {
    this.submited = true;
    if (this.formDetails.valid && this.fileExceedData === false) {
      this.currentFileUpload = this.selectedFiles.item(0);
      const formData = new FormData();
      formData.append('file', this.currentFileUpload);
      formData.append('projectID', this.formDetails.value.projectID);
      formData.append('description', this.formDetails.value.description);
      this.projectSerivce.pm002003CreateDocument(formData).subscribe(
        (res: any) => {
          if (res.error === null) {
            this.ReGetListDocument.emit(true);
            this.translateService.get('notification-message.save-success').subscribe((text: string) => {
              this.toastService.show(text, { classname: 'bg-success text-light', delay: 3000 });
              this.backToList(false);
            });
          }
        },
        fail => {
          if (fail.error.error.code.slice(-3) === '904') {
            this.fileExceedData = true;
          }
        }
      );
    }
  }

  displayMessageError(errors: any) {
    const formControl = this.formDetails.get(errors.itemName);
    if (formControl) {
      this.translateService.getTranslation(errors.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = errors.code;
        this.displayMessage = errorMessage;
        formControl.setErrors({
          serverError: errorMessage
        });
      });
    }
  }

  backToList = (value: boolean) => {
    this.navigateToDocument.emit(value);
  }

  bytesToMegaBytes(bytes: number) {
    return bytes / this.MEGABYTE;
  }

}
