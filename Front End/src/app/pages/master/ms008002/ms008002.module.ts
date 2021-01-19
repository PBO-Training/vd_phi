import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS008002Component } from './ms008002.component';

const routes: Routes = [
  { path: '', component: MS008002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule

  ],
  exports: [],
  declarations: [MS008002Component],
  providers: []
})
export class MS008002Module { }
