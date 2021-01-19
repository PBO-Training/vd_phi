import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS019002Component } from './ms019002.component';

const routes: Routes = [
  { path: '', component: MS019002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS019002Component],
  providers: [],
})
export class MS019002Module { }
