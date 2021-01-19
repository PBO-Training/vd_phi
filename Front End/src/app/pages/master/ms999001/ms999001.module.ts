import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { NgApexchartsModule } from 'ng-apexcharts';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { Ms999001Component } from './ms999001.component';


const routes: Routes = [
  {path: '', component: Ms999001Component}
];

@NgModule({
  exports: [],
  declarations: [Ms999001Component],
  providers: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    NgApexchartsModule,
    SharedModule
  ]
})
export class Ms999001Module { }
