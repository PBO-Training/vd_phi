import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS002002Component } from './ms002002.component';

const routes: Routes = [
    { path: '', component: MS002002Component }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule

    ],
    exports: [],
    declarations: [MS002002Component],
    providers: []
})
export class MS002002Module { }
