import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { Notfound404Component } from './notfound404.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';

const routes: Routes = [
  {path: '', component: Notfound404Component}
];

@NgModule({
  exports: [],
  declarations: [Notfound404Component],
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
export class Notfound404Module { }
