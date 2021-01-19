import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS003001Component } from './ms003001.component';

const routes: Routes = [
  {
    path: '',
    component: MS003001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS003001Component],
  providers: []
})
export class MS003001Module { }
