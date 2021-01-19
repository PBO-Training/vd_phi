import { SharedModule } from 'src/app/theme/shared/shared.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MS005001SearchComponent } from './ms005001.component';

const routes: Routes = [
  {
    path: '',
    component: MS005001SearchComponent,
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    SharedModule
  ],
  exports: [],
  declarations: [MS005001SearchComponent],
  providers: []
})
export class MS005001SearchModule { }
