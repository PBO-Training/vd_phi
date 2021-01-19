import { SharedModule } from 'src/app/theme/shared/shared.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MS018001Component } from './ms018001.component';

const routes: Routes = [
  {
    path: '',
    component: MS018001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS018001Component],
  providers: []
})
export class MS018001Module { }
