import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { PopupModule } from '../../../theme/shared/components/popup/popup.module';
import { SharedModule } from '../../../theme/shared/shared.module';
import { StoreTabService } from '../../../services/storage/storage-tab.service';
import { Pm001002TabDocumentDetailComponent } from './pm001002-tab-document-detail/pm001002-tab-document-detail.component';
import { Pm001002TabDocumentComponent } from './pm001002-tab-document/pm001002-tab-document.component';
import { Pm001002TabInfoViewComponent } from './pm001002-tab-info-view/pm001002-tab-info-view.component';
import { Pm001002TabInfoComponent } from './pm001002-tab-info/pm001002-tab-info.component';
import { Pm001002IssueDetailComponent } from './pm001002-tab-issue-detail/pm001002-tab-issue-detail.component';
import { Pm001002SearchIssueComponent } from './pm001002-tab-issue/pm001002-tab-issue.component';
import { Pm001002MemberDetailComponent } from './pm001002-tab-member-detail/pm001002-tab-member-detail.component';
import { Pm001002TabMemberAddMembersComponent } from './pm001002-tab-member-add-members/pm001002-tab-member-add-members.component';
// tslint:disable-next-line: max-line-length
import { Pm001002TabMemberCompleteReviewComponent } from './pm001002-tab-member-complete-review-members/pm001002-tab-member-complete-review.component';
import { Pm001002TabListMemberComponent } from './pm001002-tab-member/pm001002-tab-member.component';
import { Pm001002TabOverviewComponent } from './pm001002-tab-overview/pm001002-tab-overview.component';
import { Pm001002Component } from './pm001002.component';
import { ModalPersonalAssessmentComponent } from './pm001002-tab-member/modal-personal-assessment/modal-personal-assessment.component';
import { NgApexchartsModule } from 'ng-apexcharts';

const ROUTES: Routes = [
  {
    path: '',
    component: Pm001002Component
  }
];
@NgModule({
  declarations: [
    Pm001002Component,
    Pm001002TabInfoComponent,
    Pm001002TabInfoViewComponent,
    Pm001002SearchIssueComponent,
    Pm001002IssueDetailComponent,
    Pm001002TabListMemberComponent,
    Pm001002TabDocumentComponent,
    Pm001002TabDocumentDetailComponent,
    Pm001002TabMemberCompleteReviewComponent,
    Pm001002TabOverviewComponent,
    Pm001002TabMemberAddMembersComponent,
    ModalPersonalAssessmentComponent,
    Pm001002MemberDetailComponent

  ],
  imports: [
    RouterModule.forChild(ROUTES),
    NgbDatepickerModule,
    PopupModule,
    NgSelectModule,
    NgbModule,
    NgApexchartsModule,
    SharedModule
  ],
  providers: [
    StoreTabService
  ],
  entryComponents: [
    ModalPersonalAssessmentComponent
  ]
})
export class Pm001002Module { }
