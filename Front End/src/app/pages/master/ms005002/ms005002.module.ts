import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS005002DetailComponent } from './ms005002.component';


const routes: Routes = [
  { path: '', component: MS005002DetailComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
    ,

  ],
  exports: [],
  declarations: [MS005002DetailComponent],
  providers: [],
})
export class MS005002DetailModule { }
