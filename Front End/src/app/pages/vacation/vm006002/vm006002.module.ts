import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { VM006002Component } from './vm006002.component';

const routes: Routes = [
  {
    path: '',
    component: VM006002Component,
  }
];

@NgModule({
  declarations: [VM006002Component],
  imports: [
    RouterModule.forChild(routes),
    NgbDatepickerModule,
    NgSelectModule,
    SharedModule
  ]
})
export class VM006002Module { }
