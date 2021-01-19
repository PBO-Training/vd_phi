import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { VM001001Component } from './vm001001.component';

const routes: Routes = [
  {
    path: '',
    component: VM001001Component,
  }
];

@NgModule({
  declarations: [VM001001Component],
  imports: [
    RouterModule.forChild(routes),
    NgbDatepickerModule,
    NgSelectModule,
    SharedModule
  ]
})
export class VM001001Module { }
