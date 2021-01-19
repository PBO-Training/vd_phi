import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS006001SearchComponent } from './ms006001.component';


const routes: Routes = [
  {
    path: '',
    component: MS006001SearchComponent,
  }
];

@NgModule({
  declarations: [MS006001SearchComponent],
  imports: [
    RouterModule.forChild(routes),
    SharedModule,
  ],
  exports: [],
  providers: [],
})
export class MS006001SearchModule { }
