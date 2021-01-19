import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-em001001-modal',
  templateUrl: './em001001-modal.component.html',
  styleUrls: ['./em001001-modal.component.scss']
})
export class Em001001ModalComponent implements OnInit {

  @Input() title;
  @Input() listSkillID;
  @Input() listSkills;
  @Input() listOldSkills;
  @Output() isOK: EventEmitter<any> = new EventEmitter();

  formSkill: FormGroup;
  get skills() { return this.formSkill.controls.listSkill as FormArray; }

  constructor(
    public activeModal: NgbActiveModal,
    public fb: FormBuilder
  ) {
    this.formSkill = this.fb.group({
      listSkill: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.createFormSkill();
  }

  createFormSkill() {
    this.listOldSkills.forEach(element => {
      const group = this.fb.group({
        skillID: [element.skillID],
        skillName: [element.skillName],
        skillExperience: [element.skillExperience]
      });
      this.skills.push(group);
    });
  }

  onClickOKButton() {
    const newSkillExpList = [];
    this.formSkill.value.listSkill.forEach(element => {
      // const toFixedOneExperience = element.skillExperience + 0.0;
      newSkillExpList.push(
        {
          skillID: element.skillID,
          skillExperience: (element.skillExperience * 1).toFixed(1),
          skillName: element.skillName
        }
      );
    });
    this.isOK.emit(newSkillExpList);
    this.activeModal.close('Close click');
  }

  onKeyDownExp(event: any) {
    // const pattern = /^\d{1,2}\.{0,1}\d{0,1}$/;
    if (!((event.keyCode > 95 && event.keyCode < 106) // number
      || (event.keyCode > 47 && event.keyCode < 58) // number
      || event.keyCode === 8 // backspace
      || event.keyCode === 46 // delete
      || (event.keyCode > 35 && event.keyCode < 41) // arrow
      || (event.keyCode === 110) // dot
      || (event.keyCode === 190))) {
      return false;
    }
  }
}
