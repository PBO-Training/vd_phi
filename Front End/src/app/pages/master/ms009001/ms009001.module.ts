import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MS009001SearchComponent } from './ms009001.component';


const routes: Routes = [
    {
        // Set path default is "customer". This path is put in "app-routing.module.ts"
        path: '',
        component: MS009001SearchComponent,
    }
];
@NgModule({
    imports: [
        RouterModule.forChild(routes),
        SharedModule
    ],
    exports: [],
    declarations: [
        MS009001SearchComponent,
    ],
    providers: [],
})
export class MS009001SearchModule { }
