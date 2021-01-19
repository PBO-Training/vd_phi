import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MS017001Component } from './ms017001.component';
import { SharedModule } from 'src/app/theme/shared/shared.module';
const routes: Routes = [
  { path: '', component: MS017001Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS017001Component],
  providers: []
})
export class MS017001Module { }
