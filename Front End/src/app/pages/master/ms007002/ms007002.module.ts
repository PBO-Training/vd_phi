import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS007002Component } from './ms007002.component';

const routes: Routes = [
  { path: '', component: MS007002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule,
  ],
  exports: [],
  declarations: [MS007002Component],
  providers: [],
})
export class MS007002Module { }
