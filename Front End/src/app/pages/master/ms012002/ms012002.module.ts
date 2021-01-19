import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS012002Component } from './ms012002.component';
const routes: Routes = [
    { path: '', component: MS012002Component }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule

    ],
    exports: [],
    declarations: [MS012002Component],
    providers: []
})
export class MS012002Module { }
