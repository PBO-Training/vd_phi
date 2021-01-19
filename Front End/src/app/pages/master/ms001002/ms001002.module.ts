import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MS001002Component } from './ms001002.component';
import { SharedModule } from '../../../theme/shared/shared.module';

const routes: Routes = [
  { path: '', component: MS001002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule

  ],
  exports: [],
  declarations: [MS001002Component],
  providers: []
})
export class MS001002Module { }
