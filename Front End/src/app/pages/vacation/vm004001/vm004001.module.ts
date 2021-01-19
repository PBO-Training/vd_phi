import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { VM004001Component } from './vm004001.component';

const routes: Routes = [
  {
    path: '',
    component: VM004001Component,
  }
];

@NgModule({
  declarations: [VM004001Component],
  imports: [
    RouterModule.forChild(routes),
    NgbDatepickerModule,
    NgSelectModule,
    SharedModule
  ]
})
export class VM004001Module { }
