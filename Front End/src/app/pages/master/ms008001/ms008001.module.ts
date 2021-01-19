import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS008001Component } from './ms008001.component';

const routes: Routes = [
  {
    path: '',
    component: MS008001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [
    MS008001Component,
  ],
  providers: [],
})
export class MS008001Module { }
