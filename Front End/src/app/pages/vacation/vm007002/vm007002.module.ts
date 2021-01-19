import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { VM007002Component } from './vm007002.component';

const routes: Routes = [
  {
    path: '',
    component: VM007002Component,
  }
];

@NgModule({
  declarations: [VM007002Component],
  imports: [
    RouterModule.forChild(routes),
    NgbDatepickerModule,
    NgSelectModule,
    SharedModule
  ]
})
export class VM007002Module { }
