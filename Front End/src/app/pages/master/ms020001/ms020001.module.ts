import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS020001Component } from './ms020001.component';

const routes: Routes = [
  {
    path: '',
    component: MS020001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS020001Component],
  providers: []
})
export class MS020001Module { }
