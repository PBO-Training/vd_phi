import { NgModule } from '@angular/core';
import { BreadcrumbShareComponent } from './breadcrumb-share.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        TranslateModule
    ],
    exports: [BreadcrumbShareComponent],
    declarations: [BreadcrumbShareComponent],
    providers: [BreadcrumbShareComponent],
})
export class BreabcrumbShareModule { }
