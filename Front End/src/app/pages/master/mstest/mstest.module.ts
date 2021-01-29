import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { MStestComponent } from './mstest.component';


const routes: Routes = [
  { path: '', component: MStestComponent }
];

@NgModule({
  imports: [
        RouterModule.forChild(routes),
        SharedModule
  ],
  exports: [],
  declarations: [MStestComponent],
    providers: []
})
export class MStestModule { }
