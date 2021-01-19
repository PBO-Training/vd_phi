import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MS017002Component } from './ms017002.component';
import { SharedModule } from 'src/app/theme/shared/shared.module';
const routes: Routes = [
  { path: '', component: MS017002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS017002Component],
  providers: []
})
export class MS017002Module { }
