import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { LengthConstant } from '../../../../../common/constant/length';
import { ActionReviews } from '../../../../../common/constant/type';
import { parseDate, DateConfig } from '../../../../../common/datepicker-config/datepicker-config';
import { DateValidator } from '../../../../../common/validator/date-validator';
import { DropDownDataMember } from '../../pm001002-entity';
import { RemoveMemberRequest } from '../pm001002-tab-member-entity';

@Component({
  selector: 'app-personal-assessment',
  templateUrl: './modal-personal-assessment.component.html',
  styleUrls: ['./modal-personal-assessment.component.scss']
})
export class ModalPersonalAssessmentComponent implements OnInit, OnChanges {
  @Input() dropdownData: DropDownDataMember;
  @Input() member: any;
  submitted = false;
  memberForm: FormGroup;
  config = new DateConfig();
  interimAssessment = ActionReviews.TEMPORARY;
  completeReview = ActionReviews.FINISHED;
  maxLenghtString = { value: LengthConstant.MAX_LENGTH_STRING };

  constructor(
    public activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
  ) {}

  get f() { return this.memberForm.controls; }

  ngOnChanges(changes: SimpleChanges): void {
    this.initData();
  }

  ngOnInit(): void {
    this.initData();
  }

  initData(): void {
    this.memberForm = this.formBuilder.group({
      employeeID: [this.member?.employeeID],
      dateJoinProject: [this.member?.dateJoinProject, Validators.required],
      dateOutProject: [this.member?.dateOutProject, Validators.required],
      positionProjectID: [this.member?.positionProjectID],
      evaluateEmployeeProjectID: [null, Validators.required],
      note: ['', Validators.maxLength(LengthConstant.MAX_LENGTH_STRING)]
    }, {
      validator: Validators.compose([DateValidator.dateLessThan('dateJoinProject', 'dateOutProject')])
    });
  }

  reviewMember(valueAction: number) {
    this.submitted = true;
    if (!this.memberForm.invalid) {
      const valueMember = this.memberForm.value;
      const listMemberRequest = Array<any>();
      const request = new RemoveMemberRequest();
      const memberRequest = {
        employeeID: valueMember.employeeID,
        positionProjectID: valueMember.positionProjectID,
        evaluateEmployeeProjectID: valueMember.evaluateEmployeeProjectID,
        dateJoinProject: parseDate(valueMember.dateJoinProject),
        dateOutProject: parseDate(valueMember.dateOutProject),
        note: valueMember.note
      };
      listMemberRequest.push(memberRequest);
      request.listEmployee = listMemberRequest;
      request.action = valueAction;
      this.activeModal.close(request);
    }
  }
}
