import {
  AfterViewInit, Component,
  EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges
} from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { LengthConstant } from '../../../../common/constant/length';
import { DateConfig } from '../../../../common/datepicker-config/datepicker-config';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CustomReactiveFormValidator } from '../../../../common/validator/custom-validator';
import { TranslationService } from '../../../../services/translate/translation.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { Language, LanguageDetail, LevelLanguage, LevelSkill, ServerErrors, Skill, SkillDetail, SkillType } from '../em001002-entity';
import { StoreService } from '../em001002-store-service';
import { Em001002ModalAddSkillComponent } from './em001002-modal-add-skill/em001002-modal-add-skill.component';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-em001002-skill-language',
  templateUrl: './em001002-skill-language.component.html',
  styleUrls: ['./em001002-skill-language.component.scss']
})
export class Em001002SkillLanguageComponent implements OnInit, AfterViewInit, OnChanges, OnDestroy {
  @Input() listSkill: Skill[];
  @Input() listLevelSkill: LevelSkill[];
  @Input() listSkillType: SkillType[];
  @Input() listSkillDetail: SkillDetail[];
  @Input() listLanguage: Language[];
  @Input() listLevelLanguage: LevelLanguage[];
  @Input() listLanguageDetail: LanguageDetail[];
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() backOrCancel: EventEmitter<any> = new EventEmitter();
  unsubscribe$ = new Subject();
  config = new DateConfig();
  formSkillLanguage: FormGroup;
  isEdit = false;
  listTypeSkillCustom = [];
  listTypeSkillDefautl: any;
  formSubmit = false;
  displayMessage: string;
  errorsCode: string;
  constructor(
    public translationService: TranslationService,
    public storeService: StoreService,
    public fb: FormBuilder,
    private modalService: NgbModal,
    public toastService: ToastService,
    private translateService: TranslateService

  ) {
    this.formSkillLanguage = this.fb.group({
      // listLanguage: this.fb.array([]),
      listSkill: this.fb.array([])
    });
  }

  get skills() { return this.formSkillLanguage.controls.listSkill as FormArray; }
  get languages() { return this.formSkillLanguage.controls.listLanguage as FormArray; }

  maxLenghtPoint = { value: LengthConstant.MAX_LENGTH_POINT };
  minLenghtPoint = { value: LengthConstant.MIN_LENGTH_POINT };

  ngAfterViewInit(): void {
    // set validator form dupplicate with param skillID
    this.formSkillLanguage.setValidators(CustomReactiveFormValidator.isDuplicate('skillID'));
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.storeService.saveValueChange(this.formSkillLanguage.value);
    this.storeService.saveStore(this.formSkillLanguage.value);
    if (changes.listSkillDetail) {
      if (changes.isEdit === undefined) {
        this.listSkillDetail = changes.listSkillDetail.currentValue;
        this.initViewSkill();
        this.listTypeSkillDefautl = JSON.parse(JSON.stringify(this.listTypeSkillCustom));
        this.createFormSkill();
      }
      this.isEdit = false;
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  ngOnInit(): void {
    this.initViewSkill();
    this.listTypeSkillDefautl = JSON.parse(JSON.stringify(this.listTypeSkillCustom));
    this.createFormSkill();

    this.storeService.isEditFormSkill.next(false);
    // form value changes save to store
    this.formSkillLanguage.valueChanges.pipe(takeUntil(this.unsubscribe$)).subscribe(
      val => {
        this.storeService.updateStatusForm(this.formSkillLanguage);
        this.storeService.saveValueChange(this.formSkillLanguage.value);
        this.formSubmit = false;
      }
    );
    this.storeService.isSubmitFormSkill.subscribe((val: boolean) => {
      this.formSubmit = val;
    });
    this.storeService.isEditFormSkill.subscribe((val: boolean) => {
      this.isEdit = val;
      this.initViewSkill();
      this.listTypeSkillDefautl = JSON.parse(JSON.stringify(this.listTypeSkillCustom));
      this.createFormSkill();
    });
  }

  createFormSkill() {
    // bind value form array
    this.skills.clear();
    if (this.listTypeSkillCustom) {
      // tslint:disable-next-line:forin
      for (const index in this.listTypeSkillCustom) {
        const group = this.fb.group({
          skillTypeID: ['' + this.listTypeSkillCustom[index]?.skillTypeID, Validators.required],
          skillID: [this.listTypeSkillCustom[index]?.skillID, Validators.required],
          levelSkillID: [this.listTypeSkillCustom[index]?.levelSkillID, Validators.required],
          skillExperience: [this.listTypeSkillCustom[index]?.skillExperience, Validators.required]
        });
        this.skills.push(group);
      }
    }
    this.storeService.saveValueChange(this.formSkillLanguage.value);
    this.storeService.saveStore(this.formSkillLanguage.value);
    CustomReactiveFormValidator.updateTreeValidity(this.formSkillLanguage);
  }

  editSkill() {
    this.isEdit = true;
    this.storeService.isEditFormSkill.next(true);
  }

  eventCancel() {
    // this.isEdit = false;
    // // this.formSubmit = false;
    // this.storeService.isSubmitFormSkill.next(false);
    // this.storeService.isEditFormSkill.next(false);
    // if (this.isEdit === false) {
    //   this.listTypeSkillCustom = JSON.parse(JSON.stringify(this.listTypeSkillDefautl));
    //   this.createFormSkill();
    // }
    this.backOrCancel.emit('back');
  }

  eventBack() {
    this.back.emit('back');
  }

  checkExistSkillType(skillTypeID: number, listTemp: any[]): boolean {
    const object = listTemp.find(item => item.skillTypeID === skillTypeID);
    if (object) {
      return true;
    }
    return false;
  }

  addTypeSkill() {
    const listSkillTypeInit: SkillType[] = [];
    this.listSkillType.forEach(element => {
      const resultFind = this.listTypeSkillCustom.find(item => item.skillTypeID === element.skillTypeID);
      if (resultFind === undefined) {
        listSkillTypeInit.push(element);
      }
    });
    if (listSkillTypeInit.length > 0) {
      const modalRef = this.modalService.open(Em001002ModalAddSkillComponent, { size: 'xl', centered: true });
      modalRef.componentInstance.listSkillType = listSkillTypeInit;
      modalRef.componentInstance.listSkill = this.listSkill;
      modalRef.componentInstance.listLevelSkill = this.listLevelSkill;
      modalRef.result.then(result => {
        if (result) {
          if (result !== 'close') {
            const group = this.fb.group({
              skillTypeID: ['' + result.skillTypeID],
              skillID: [result.skillID, Validators.required],
              levelSkillID: [result.levelSkillID, Validators.required],
              skillExperience: [result.skillExperience, Validators.required]
            });
            this.skills.push(group);
            this.listTypeSkillCustom.push({ total: 1, endGroup: true, ...result });
          }
        }
      });

      this.storeService.saveValueChange(this.formSkillLanguage.value);
    } else {
      this.translateService.get('employee.create.notification.full-skill-type').subscribe((text: string) => {
        this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
      });
    }
  }

  addSkill(skillTypeID, index) {
    const group = this.fb.group({
      skillTypeID: ['' + skillTypeID],
      skillID: [null, Validators.required],
      levelSkillID: [this.listLevelSkill[0]?.levelSkillID, Validators.required],
      skillExperience: [0, Validators.required],
    });
    const skill = {
      skillTypeID: skillTypeID,
      skillTypeName: this.listTypeSkillCustom[index].skillTypeName,
      skillID: null,
      skillName: null,
      levelSkillID: this.listLevelSkill[0]?.levelSkillID,
      levelSkillName: this.listLevelSkill[0]?.levelSkillName,
      skillExperience: 0,
      endGroup: true
    };
    // insert index 0 to form array
    this.skills.insert(index + 1, group);
    this.increaseTypeSkill(skillTypeID);
    this.listTypeSkillCustom.splice(index + 1, 0, skill);

    this.storeService.saveValueChange(this.formSkillLanguage.value);
  }

  deleteSkill(skillTypeID, index: number) {
    this.skills.removeAt(index);
    this.decreaseTypeSkill(skillTypeID, index);
  }

  increaseTypeSkill(skillTypeID) {
    this.listTypeSkillCustom.forEach(element => {
      if (element.skillTypeID === skillTypeID && element.total) {
        element.total = element.total + 1;
      }
      if (element.skillTypeID === skillTypeID && element.endGroup) {
        element.endGroup = false;
      }
    });
  }

  decreaseTypeSkill(skillTypeID, index) {
    if (this.listTypeSkillCustom[index].endGroup === false) {
      if (this.listTypeSkillCustom[index].total) {
        this.listTypeSkillCustom[index + 1].total = this.listTypeSkillCustom[index].total - 1;
        this.listTypeSkillCustom[index + 1].skillTypeName = this.listTypeSkillCustom[index].skillTypeName;
      } else {
        this.listTypeSkillCustom.forEach((element, i) => {
          if (element.skillTypeID === skillTypeID && element.total) {
            element.total = element.total - 1;
          }
        });
      }
    } else if (this.listTypeSkillCustom[index].endGroup === true && index !== 0) {
      this.listTypeSkillCustom.forEach((element, i) => {
        if (element.skillTypeID === skillTypeID && element.total) {
          element.total = element.total - 1;
        }
      });
      if (this.listTypeSkillCustom[index].endGroup === true) {
        this.listTypeSkillCustom[index - 1].endGroup = true;
      }
    }
    this.listTypeSkillCustom.splice(index, 1);
  }

  // submitFormEvent() {
  //   console.log(this.skills);
  //   this.formSubmit = true;
  //   CustomReactiveFormValidator.updateTreeValidity(this.formSkillLanguage);
  //   // updateTreeValidity(this.formSkill);
  //   if (this.formSkillLanguage.valid) {
  //     // form = this.formSkill.value;
  //     this.submitForm.emit(this.formSkillLanguage.value);
  //   }
  // }

  submit(value) {
    this.formSubmit = true;
    CustomReactiveFormValidator.updateTreeValidity(this.formSkillLanguage);
    // updateTreeValidity(this.formSkill);
    if (this.formSkillLanguage.valid) {
      // form = this.formSkill.value;
      this.submitForm.emit(value);
    }
  }

  onKeyDownExp(event: any) {
    if (!((event.keyCode > 95 && event.keyCode < 106)
      || (event.keyCode > 47 && event.keyCode < 58)
      || event.keyCode === 8
      || (event.keyCode === 110)
      || (event.keyCode === 190))) {
      return false;
    }
  }

  initViewSkill() {
    this.listTypeSkillCustom = [];
    const listTemp = [];
    this.listSkillDetail.forEach(element => {
      if (this.checkExistSkillType(element.skillTypeID, listTemp)) {
        const object = listTemp.find(item => item.skillTypeID === element.skillTypeID);
        if (object) {
          object.listSkill.push(element);
        }
      } else {
        listTemp.push({ skillTypeID: element.skillTypeID, listSkill: [element] });
      }
    });
    listTemp.forEach(element => {
      element.listSkill.forEach((item, index) => {
        if (element.listSkill.length === 1) {
          this.listTypeSkillCustom.push({ total: element.listSkill.length, endGroup: true, ...item });
        } else {
          if (index === 0) {
            this.listTypeSkillCustom.push({ total: element.listSkill.length, endGroup: false, ...item });
          } else if (index === element.listSkill.length - 1) {
            this.listTypeSkillCustom.push({ endGroup: true, ...item });
          } else {
            this.listTypeSkillCustom.push({ endGroup: false, ...item });
          }
        }
      });
    });
  }
  limitDecimalPlaces(e, count) {
    if (e.target.value.indexOf('.') === -1) { return; }
    if ((e.target.value.length - e.target.value.indexOf('.')) > count) {
      e.target.value = parseFloat(e.target.value).toFixed(count);
    }
  }
}
