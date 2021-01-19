import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { LevelSkill, Skill, SkillType } from '../../em001002-entity';

@Component({
  selector: 'app-em001002-modal-add-skill',
  templateUrl: './em001002-modal-add-skill.component.html',
  styleUrls: ['./em001002-modal-add-skill.component.scss']
})
export class Em001002ModalAddSkillComponent implements OnInit {
  @Input() listSkill: Skill[];
  @Input() listLevelSkill: LevelSkill[];
  @Input() listSkillType: SkillType[];
  @Input() listSkillUsed: [];
  formSkill: FormGroup;
  formSubmit = false;
  constructor(
    public activeModal: NgbActiveModal,
    public fb: FormBuilder,
  ) {

  }
  get skills() { return this.formSkill.controls; }
  ngOnInit(): void {
    this.formSkill = this.fb.group({
      skillTypeID: [null, Validators.required],
      skillID: [null, Validators.required],
      // levelSkillID: [null, Validators.required]
      // skillTypeID: [this.listSkillType[0].skillTypeID, Validators.required],
      // skillID: [this.listSkill.find(item => item.skillTypeID === this.listSkillType[0].skillTypeID)?.skillID, Validators.required],
      levelSkillID: [this.listLevelSkill[0].levelSkillID, Validators.required],
      skillExperience: [0, Validators.required]
    });

    this.formSkill.controls.skillTypeID.valueChanges.subscribe(val => {
      this.formSkill.patchValue({ skillID: null });
    });
    this.formSkill.valueChanges.subscribe(val => {
      this.formSubmit = false;
    });
  }

  addSkill() {
    this.formSubmit = true;
    if (this.formSkill.valid) {
      const skillTypeName = this.listSkillType.find(item => +item.skillTypeID === +this.formSkill.value.skillTypeID)?.skillTypeName;
      const skillName = this.listSkill.find(item => +item.skillID === +this.formSkill.value.skillID)?.skillName;
      const value = { skillTypeName, skillName, ... this.formSkill.value };
      this.activeModal.close(value);
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
}
