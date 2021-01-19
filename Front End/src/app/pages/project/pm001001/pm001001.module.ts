import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbCollapseModule, NgbDatepickerModule, NgbPaginationModule, NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { TranslateModule } from '@ngx-translate/core';
import { BreabcrumbShareModule } from '../../../theme/shared/components';
import { Pm001001SearchComponent } from './pm001001.component';
import { SharedModule } from '../../../theme/shared/shared.module';


const ROUTES: Routes = [
  {
    path: '',
    component: Pm001001SearchComponent
  }
];
@NgModule({
  declarations: [Pm001001SearchComponent],
  imports: [
    RouterModule.forChild(ROUTES),
    FormsModule,
    NgbDatepickerModule,
    NgSelectModule,
    SharedModule,
    NgbProgressbarModule
  ]
})
export class Pm001001SearchModule { }
