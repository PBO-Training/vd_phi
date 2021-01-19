import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { VM003001Component } from './vm003001.component';

const routes: Routes = [
  {
    path: '',
    component: VM003001Component,
  }
];

@NgModule({
  declarations: [VM003001Component],
  imports: [
    RouterModule.forChild(routes),
    NgbDatepickerModule,
    NgSelectModule,
    SharedModule
  ]
})
export class VM003001Module { }
