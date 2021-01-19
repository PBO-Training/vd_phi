import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS009002Component } from './ms009002.component';


const routes: Routes = [
    {
        // Set path default is "customer/create". This path is put in "app-routing.module.ts"
        path: '',
        component: MS009002Component,
    }
];
@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule
    ],
    exports: [],
    declarations: [
        MS009002Component,
    ],
    providers: [],
})
export class MS009002DetailModule { }
