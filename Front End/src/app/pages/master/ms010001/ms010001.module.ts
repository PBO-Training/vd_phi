import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS010001Component } from './ms010001.component';

const routes: Routes = [
  {
    path: '',
    component: MS010001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS010001Component],
  providers: []
})
export class MS010001Module { }
