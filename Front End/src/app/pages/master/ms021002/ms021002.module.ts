import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { MStestComponent } from '../mstest/mstest.component';
import { MS021002Component } from './ms021002.component';

const routes: Routes = [
  { path: '', component: MS021002Component }
];

@NgModule({
  imports: [
        RouterModule.forChild(routes),
        SharedModule
  ],
  exports: [],
  declarations: [MS021002Component, MStestComponent],
    providers: []
})
export class MS021002Module { }
