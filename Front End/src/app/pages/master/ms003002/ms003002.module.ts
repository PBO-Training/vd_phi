import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS003002Component } from './ms003002.component';

const routes: Routes = [
  { path: '', component: MS003002Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule,
  ],
  exports: [],
  declarations: [MS003002Component],
  providers: [],
})
export class MS003002Module { }
