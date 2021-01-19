import { SharedModule } from 'src/app/theme/shared/shared.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MS019001Component } from './ms019001.component';

const routes: Routes = [
  {
    path: '',
    component: MS019001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS019001Component],
  providers: []
})
export class MS019001Module { }
