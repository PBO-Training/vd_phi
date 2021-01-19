import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS018002Component } from './ms018002.component';

const routes: Routes = [
  { path: '', component: MS018002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS018002Component],
  providers: [],
})
export class MS018002Module { }
