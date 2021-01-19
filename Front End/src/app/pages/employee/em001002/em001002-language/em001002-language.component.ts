import {
  AfterViewInit, Component, ElementRef, EventEmitter,
  Input, OnChanges, OnDestroy, OnInit, Output, QueryList, SimpleChanges, ViewChildren
} from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { take, takeUntil } from 'rxjs/operators';
import { DateConfig, formatDate, parseNewDateJs } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CustomReactiveFormValidator } from '../../../../common/validator/custom-validator';
import { TranslationService } from '../../../../services/translate/translation.service';
import { Language, LanguageCategory, LanguageDetail, LevelLanguage, ServerErrors } from '../em001002-entity';
import { StoreService } from '../em001002-store-service';

@Component({
  selector: 'app-em001002-language',
  templateUrl: './em001002-language.component.html',
  styleUrls: ['./em001002-language.component.scss']
})
export class Em001002LanguageComponent implements OnInit, AfterViewInit, OnChanges, OnDestroy {
  @ViewChildren('appLanguage', { read: ElementRef }) appLanguage: QueryList<ElementRef>;

  @Input() listLanguage: Language[];
  @Input() listLevelLanguage: LevelLanguage[];
  @Input() listLanguageCategory: LanguageCategory[];
  @Input() listLanguageDetail: LanguageDetail[];
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isAddNewLanguage: boolean;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() backOrCancel: EventEmitter<any> = new EventEmitter();
  submited: boolean;
  checkRemove: boolean;
  unsubscribe$ = new Subject();
  config = new DateConfig();

  formLanguage: FormGroup;
  displayMessage: string;
  errorsCode: string;



  get languages() { return this.formLanguage.controls.listLanguage as FormArray; }

  constructor(
    public storeService: StoreService,
    public fb: FormBuilder,
    public translationService: TranslationService,

  ) {
    this.formLanguage = this.fb.group({
      listLanguage: this.fb.array([])
    });
  }
  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  ngOnChanges(changes: SimpleChanges): void {
    // save store current data
    this.storeService.saveValueChange(this.formLanguage.value);
    this.storeService.saveStore(this.formLanguage.value);
  }
  ngAfterViewInit(): void {
    // set validator after view init
    this.formLanguage.setValidators(CustomReactiveFormValidator.isDuplicate('languageID'));
  }
  createFormLanguage() {
    if (this.listLanguageDetail) {
      // tslint:disable-next-line:prefer-for-of
      for (let index = 0; index < this.listLanguageDetail.length; index++) {
        const group = this.fb.group({
          languageCategoryID: [this.listLanguageDetail[index]?.languageCategoryID, Validators.required],
          languageID: [this.listLanguageDetail[index]?.languageID, Validators.required],
          languageCertificate: [this.listLanguageDetail[index]?.languageCertificate, Validators.required],
          expirationDate: [formatDate(this.listLanguageDetail[index]?.expirationDate), Validators.required]
        });
        this.languages.push(group);
      }
    }
    this.storeService.saveValueChange(this.formLanguage.value);
    this.storeService.saveStore(this.formLanguage.value);
  }
  ngOnInit(): void {
    if (this.isAddNewLanguage) {
      this.addNewLanguage();
    } else {
      this.createFormLanguage();
    }
    // check languageCategory change to language
    this.changeLanguageCategory();
    this.formLanguage.valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(val => {
      this.storeService.saveValueChange(this.formLanguage.value);
      this.storeService.updateStatusForm(this.formLanguage);

    });
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$)).subscribe(event => {
      this.translationService.getErrorsChangeTranslation(this.errorsCode).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors display after change language
        this.displayMessage = errorMessage;
      });
    });
    // handle server Errors
    this.serverErrors$.pipe(takeUntil(this.unsubscribe$)).subscribe(
      value => {
        if (!value) {
          return;
        }
        this.displayMessageError(value);
      }
    );
  }

  submit(form: any) {
    this.submited = true;
    CustomReactiveFormValidator.updateTreeValidity(this.formLanguage);
    if (this.formLanguage.valid) {
      this.submitForm.emit(form);
    }
  }
  addLanguage() {
    this.submited = false;
    const group = this.fb.group({
      languageCategoryID: [null, Validators.required],
      languageID: [null, Validators.required],
      languageCertificate: ['', Validators.required],
      expirationDate: [formatDate(parseNewDateJs(new Date()))]
    });
    this.languages.insert(0, group);
    // focus first element when create new language
    this.appLanguage.changes.pipe(take(1)).subscribe({
      next: changes => changes.first.nativeElement.focus()
    });
    this.changeLanguageCategory();
  }
  deleteLanguage(index: number) {
    this.languages.removeAt(index);
    if (this.languages.length === 0) {
      this.checkRemove = true;
    }
  }
  displayMessageError(erros: ServerErrors) {
    if (this.languages.get(erros.itemName)) {
      const hasDuplicate = [];
      let idx = this.languages.value.indexOf(erros.itemName);
      while (idx !== -1) {
        hasDuplicate.push(idx);
        idx = this.languages.value.indexOf(erros.itemName, idx + 1);
      }
      if (hasDuplicate.length < 1) {
        return;
      }
      this.translationService.getTranslation(erros.code).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors formcontrol
        this.errorsCode = erros.code;
        this.displayMessage = errorMessage;
      });
      hasDuplicate.map(index => {
        const formControl = this.languages.controls[index].get(erros.itemName);
        formControl.setErrors({
          serverError: this.displayMessage
        });
      });
    }
  }
  openPopup() {
    this.back.emit('back');
  }

  addNewLanguage() {
    this.submited = false;
    const group = this.fb.group({
      languageCategoryID: [null, Validators.required],
      languageID: [null, Validators.required],
      languageCertificate: ['', Validators.required],
      expirationDate: [formatDate(parseNewDateJs(new Date()))]
    });
    this.languages.insert(0, group);
    this.storeService.saveValueChange(this.formLanguage.value);
    this.storeService.updateStatusForm(this.formLanguage);
  }

  openPopupLanguage() {
    this.backOrCancel.emit('back');
  }

  changeLanguageCategory() {
    this.languages.controls.map(control => control.get('languageCategoryID').valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(
      val => {
        control.patchValue({
          languageID: null,
        },
          { emitEvent: false }
        );
        control.updateValueAndValidity({ emitEvent: false });
      }));
  }
}
