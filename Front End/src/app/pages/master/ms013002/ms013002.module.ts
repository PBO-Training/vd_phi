import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from './../../../theme/shared/shared.module';
import { MS013002DetailComponent } from './ms013002.component';

const routes: Routes = [
  {
    path: '',
    component: MS013002DetailComponent,
  }
];

@NgModule({
  declarations: [MS013002DetailComponent],
  providers: [],
  imports: [
    SharedModule,
    RouterModule.forChild(routes),
    NgbDatepickerModule,
  ],
  exports: [],
})
export class MS013002DetailModule { }
