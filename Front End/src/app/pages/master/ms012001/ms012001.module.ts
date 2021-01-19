import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS012001Component } from './ms012001.component';

const routes: Routes = [
  {
    path: '',
    component: MS012001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS012001Component],
  providers: []
})
export class MS012001Module { }
