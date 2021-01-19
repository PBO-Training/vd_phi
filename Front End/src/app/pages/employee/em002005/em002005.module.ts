import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EM002005Component } from './em002005.component';
import { SharedModule } from '../../../theme/shared/shared.module';

const routes: Routes = [
  { path: '', component: EM002005Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule

  ],
  exports: [],
  declarations: [EM002005Component],
  providers: []
})
export class EM002005Module { }
