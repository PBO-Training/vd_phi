import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS004002Component } from './ms004002.component';

const routes: Routes = [
  { path: '', component: MS004002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule

  ],
  exports: [],
  declarations: [MS004002Component],
  providers: []
})
export class MS004002Module { }
