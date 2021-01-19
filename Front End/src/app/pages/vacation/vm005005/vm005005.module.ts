import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { VM005005Component } from './vm005005.component';

//import { AxiomSchedulerModule } from 'axiom-scheduler';

const routes: Routes = [
  {
    path: '',
    component: VM005005Component,
  }
];

@NgModule({
  declarations: [VM005005Component],
  imports: [
    RouterModule.forChild(routes),
    NgbDatepickerModule,
    NgSelectModule,
    SharedModule, 
    
    //AxiomSchedulerModule
  ]  
})
export class VM005005Module { }
