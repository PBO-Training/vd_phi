import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS004001Component } from './ms004001.component';

const routes: Routes = [
  {
    path: '',
    component: MS004001Component,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [
    MS004001Component,
  ],
  providers: [],
})
export class MS004001Module { }
