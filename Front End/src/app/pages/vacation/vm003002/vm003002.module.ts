import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { BreabcrumbShareModule } from '../../../theme/shared/components/breadcrumb-share/breabcrumb-share.module';
import { Vm003002Component } from './Vm003002.component';
import { SharedModule } from '../../../theme/shared/shared.module';

const routes: Routes = [{ path: '', component: Vm003002Component }];
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
        Vm003002Component,
    ],
    providers: [
    ],
})
export class Vm003002Module { }
