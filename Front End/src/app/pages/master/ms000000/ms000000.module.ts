import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { BreabcrumbShareModule } from '../../../theme/shared/components/breadcrumb-share/breabcrumb-share.module';
import { ModalConfirmModule } from '../../../theme/shared/components/modal-confirm/modal-confirm.module';
import { Ms000000Component } from './ms000000.component';

const routes: Routes = [
  { path: '', component: Ms000000Component }
];

@NgModule({
  exports: [],
  declarations: [Ms000000Component],
  providers: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    BreabcrumbShareModule,
    ModalConfirmModule,
  ]
})
export class Ms000000Module { }
