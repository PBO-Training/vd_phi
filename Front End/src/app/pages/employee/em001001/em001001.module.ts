import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbButtonsModule, NgbDatepickerModule, NgbDropdownModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { Em001001ModalComponent } from './em001001-modal/em001001-modal.component';
import { Em001001Component } from './em001001.component';

const routes: Routes = [
  {
    path: '',
    component: Em001001Component,
  }
];

@NgModule({
  declarations: [Em001001Component, Em001001ModalComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    NgbDatepickerModule,
    FormsModule,
    NgSelectModule,
    SharedModule,
    NgbDropdownModule,
    NgbButtonsModule,
    NgbTooltipModule
  ]
})
export class EM001001Module { }

