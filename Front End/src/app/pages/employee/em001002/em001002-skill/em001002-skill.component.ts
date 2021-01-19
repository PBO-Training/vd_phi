import {
  AfterViewInit, Component, ElementRef,
  EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, QueryList, SimpleChanges, ViewChildren
} from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, Subscription } from 'rxjs';
import { take, takeUntil } from 'rxjs/operators';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CustomReactiveFormValidator } from '../../../../common/validator/custom-validator';
import { TranslationService } from '../../../../services/translate/translation.service';
import { LevelSkill, ServerErrors, Skill, SkillDetail, SkillType } from '../em001002-entity';
import { StoreService } from '../em001002-store-service';

@Component({
  selector: 'app-em001002-skill',
  templateUrl: './em001002-skill.component.html',
  styleUrls: ['./em001002-skill.component.scss']
})
export class Em001002SkillComponent implements OnInit, OnDestroy, OnChanges, AfterViewInit {
  @ViewChildren('appSkill', { read: ElementRef }) appSkill: QueryList<ElementRef>;
  @Input() listSkill: Skill[];
  @Input() listLevelSkill: LevelSkill[];
  @Input() listSkillType: SkillType[];
  @Input() listSkillDetail: SkillDetail[];
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isAddNewSkill: boolean;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() backOrCancel: EventEmitter<any> = new EventEmitter();
  unsubscribe$ = new Subject();
  // skillTypeID = new FormControl();
  formSkill: FormGroup;
  displayMessage: string;
  errorsCode: string;
  subscription: Subscription;
  submited: boolean;
  checkRemove: boolean;
  typeSkillID = new FormControl();
  get skills() { return this.formSkill.controls.listSkill as FormArray; }

  constructor(
    public translationService: TranslationService,
    public storeService: StoreService,
    public fb: FormBuilder,
  ) {
    this.formSkill = this.fb.group({
      listSkill: this.fb.array([])
    });
  }
  ngOnChanges(changes: SimpleChanges): void {
    // save status store and valuechange
    this.storeService.saveValueChange(this.formSkill.value);
    this.storeService.saveStore(this.formSkill.value);
  }
  ngAfterViewInit(): void {
    // set validator form dupplicate with param skillID
    this.formSkill.setValidators(CustomReactiveFormValidator.isDuplicate('skillID'));
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  createFormSkill() {
    // bind value form array
    if (this.listSkillDetail) {
      // tslint:disable-next-line:forin
      for (const index in this.listSkillDetail) {
        const group = this.fb.group({
          skillID: ['' + this.listSkillDetail[index]?.skillID, Validators.required],
          levelSkillID: ['' + this.listSkillDetail[index]?.levelSkillID, Validators.required],
          skillTypeID: ['' + this.listSkillDetail[index]?.skillTypeID],
          skillExperience: ['' + this.listSkillDetail[index]?.skillExperience]
        }
        );
        this.skills.push(group);
      }
    }
    this.storeService.saveValueChange(this.formSkill.value);
    this.storeService.saveStore(this.formSkill.value);
  }
  ngOnInit(): void {
    // bind data to form skill
    if (this.isAddNewSkill) {
      this.addNewSkill();
    } else {
      this.createFormSkill();
    }
    // save store current data
    this.checkRemove = false;
    // check typeskill change to skill
    this.skills.controls.map(control => control.get('skillTypeID').valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(
      val => {
        control.patchValue({
          skillID: this.listSkill.filter(s => s.skillTypeID === +val)[0] ?
            this.listSkill.filter(s => s.skillTypeID === +val)[0].skillID ?
              this.listSkill.filter(s => s.skillTypeID === +val)[0].skillID.toString() : null : null,
        },
          { emitEvent: false }
        );
        control.updateValueAndValidity({ emitEvent: false });
        // this.storeService.saveValueChange(this.formSkill.value);
      }));
    // form value changes save to store
    this.formSkill.valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(
      val => {
        // update status form when submit in parent component
        this.storeService.updateStatusForm(this.formSkill);
        this.storeService.saveValueChange(this.formSkill.value);
      }
    );
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$)).subscribe(event => {
      this.translationService.getErrorsChangeTranslation(this.errorsCode).pipe(takeUntil(this.unsubscribe$)).subscribe(errorMessage => {
        // set errors display after change language
        this.displayMessage = errorMessage;
      });
    });
    // handle server Errors
    this.serverErrors$.pipe(takeUntil(this.unsubscribe$)).subscribe(
      value => {
        if (value) {
          this.displayMessageError(value);
        }
      }
    );
  }
  submit(form: any) {
    this.submited = true;
    CustomReactiveFormValidator.updateTreeValidity(this.formSkill);
    // updateTreeValidity(this.formSkill);
    if (this.formSkill.valid) {
      // form = this.formSkill.value;
      this.submitForm.emit(form);
    }
  }
  addSkill() {
    this.submited = false;
    const group = this.fb.group({
      skillID: [this.listSkill.filter(s => s.skillTypeID === this.listSkillType[0]?.skillTypeID)[0] ?
        this.listSkill.filter(s => s.skillTypeID === this.listSkillType[0]?.skillTypeID)[0].skillID ?
          this.listSkill.filter(s => s.skillTypeID === this.listSkillType[0]?.skillTypeID)[0].skillID.toString() : null : null,
      Validators.required],
      levelSkillID: ['' + this.listLevelSkill[0]?.levelSkillID, Validators.required],
      skillTypeID: ['' + this.listSkillType[0]?.skillTypeID],
      skillExperience: [0]
    });
    // insert index 0 to form array
    this.skills.insert(0, group);
    // focus first element when create new skill
    this.appSkill.changes.pipe(take(1)).subscribe({
      next: changes => changes.first.nativeElement.focus()
    });
    this.skills.controls.map(control => control.get('skillTypeID').valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(
      val => {
        control.patchValue({
          skillID: this.listSkill.filter(s => s.skillTypeID === +val)[0] ?
            this.listSkill.filter(s => s.skillTypeID === +val)[0].skillID ?
              this.listSkill.filter(s => s.skillTypeID === +val)[0].skillID.toString() : null : null,
        },
          { emitEvent: false }
        );
        control.updateValueAndValidity({ emitEvent: false });
      }));
  }
  deleteSkill(index: number) {
    this.skills.removeAt(index);
    if (this.skills.length === 0) {
      this.checkRemove = true;
    }
  }
  displayMessageError(erros: ServerErrors) {
    if (this.skills.get(erros.itemName)) {
      const hasDuplicate = [];
      let idx = this.skills.value.indexOf(erros.itemName);
      while (idx !== -1) {
        hasDuplicate.push(idx);
        idx = this.skills.value.indexOf(erros.itemName, idx + 1);
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
        const formControl = this.skills.controls[index].get(erros.itemName);
        formControl.setErrors({
          serverError: this.displayMessage
        });
      });
    }
  }
  openPopup() {
    this.back.emit('back');
  }

  addNewSkill() {
    const group = this.fb.group({
      skillID: [this.listSkill.filter(s => s.skillTypeID === this.listSkillType[0]?.skillTypeID)[0] ?
        this.listSkill.filter(s => s.skillTypeID === this.listSkillType[0]?.skillTypeID)[0].skillID ?
          this.listSkill.filter(s => s.skillTypeID === this.listSkillType[0]?.skillTypeID)[0].skillID.toString() : null : null,
      Validators.required],
      levelSkillID: ['' + this.listLevelSkill[0]?.levelSkillID, Validators.required],
      skillTypeID: ['' + this.listSkillType[0]?.skillTypeID],
      skillExperience: [0]
    });
    // insert index 0 to form array
    this.skills.insert(0, group);
    this.storeService.updateStatusForm(this.formSkill);
    this.storeService.saveValueChange(this.formSkill.value);
  }

  openPopupSkill() {
    this.backOrCancel.emit('back');
  }
}
