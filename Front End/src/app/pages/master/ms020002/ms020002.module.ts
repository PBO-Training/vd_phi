import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS020002Component } from './ms020002.component';
const routes: Routes = [
    { path: '', component: MS020002Component }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule

    ],
    exports: [],
    declarations: [MS020002Component],
    providers: []
})
export class MS020002Module { }
