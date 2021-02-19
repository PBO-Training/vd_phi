import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { MS021001Component } from './ms021001.component';

const routes: Routes = [
  { path: '', component: MS021001Component }
];

@NgModule({
  imports: [
        RouterModule.forChild(routes),
        SharedModule,
        NgSelectModule
  ],
  exports: [],
  declarations: [MS021001Component],
    providers: []
})
export class MS021001Module { }
