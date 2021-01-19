import { SharedModule } from './../../../theme/shared/shared.module';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MS013001SearchComponent } from './ms013001.component';

const routes: Routes = [
  {
    path: '',
    component: MS013001SearchComponent,
  }
];

@NgModule({
  declarations: [MS013001SearchComponent],
  providers: [],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
})
export class MS013001SearchModule { }
