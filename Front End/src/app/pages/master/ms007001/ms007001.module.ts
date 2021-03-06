import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS007001Component } from './ms007001.component';

const routes: Routes = [
  {
    path: '',
    component: MS007001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS007001Component],
  providers: []
})
export class MS007001Module { }
