import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS010002Component } from './ms010002.component';
const routes: Routes = [
    { path: '', component: MS010002Component }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule

    ],
    exports: [],
    declarations: [MS010002Component],
    providers: []
})
export class MS010002Module { }
