import { SharedModule } from 'src/app/theme/shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MS006002DetailComponent } from './ms006002.component';

const routes: Routes = [
  { path: '', component: MS006002DetailComponent }
];

@NgModule({
  declarations: [MS006002DetailComponent],
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  providers: []
})
export class MS006002DetailModule { }
