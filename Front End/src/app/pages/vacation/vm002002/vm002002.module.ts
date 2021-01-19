import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { BreabcrumbShareModule } from '../../../theme/shared/components/breadcrumb-share/breabcrumb-share.module';
import { Vm002002Component } from './vm002002.component';
import { SharedModule } from '../../../theme/shared/shared.module';

const routes: Routes = [{ path: '', component: Vm002002Component }];
@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        NgbModule,
        // NgbPagination,
        BreabcrumbShareModule,
        TranslateModule,
        ReactiveFormsModule,
        SharedModule

    ],
    exports: [],
    declarations: [
        Vm002002Component,
    ],
    providers: [
    ],
})
export class Vm002002Module { }
