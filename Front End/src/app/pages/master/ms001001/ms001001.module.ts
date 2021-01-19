import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MS001001Component } from './ms001001.component';
import { SharedModule } from '../../../theme/shared/shared.module';

const routes: Routes = [
    {
        path: '',
        component: MS001001Component,
    }
];
@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule
    ],
    exports: [],
    declarations: [
        MS001001Component,
    ],
    providers: [],
})
export class MS001001Module { }
