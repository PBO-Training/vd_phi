import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Forbidden403Component } from './forbidden403.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';


const routes: Routes = [
  {path: '', component: Forbidden403Component}
];

@NgModule({
  exports: [],
  declarations: [Forbidden403Component],
  providers: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
  ]
})
export class Forbidden403Module { }
