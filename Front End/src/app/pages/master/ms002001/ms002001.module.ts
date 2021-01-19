import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS002001Component } from './ms002001.component';

const routes: Routes = [
    {
        path: '',
        component: MS002001Component,
    }
];
@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule
    ],
    exports: [],
    declarations: [
        MS002001Component,
    ],
    providers: [],
})
export class MS002001Module { }
