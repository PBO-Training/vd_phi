import { NgModule } from '@angular/core';
import { ToastContainerComponent } from './toast-container.component';
import { NgbToastModule } from '../toast/toast.module';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';


@NgModule({
    imports: [
        NgbToastModule,
        CommonModule,
        TranslateModule
    ],
    exports: [ToastContainerComponent],
    declarations: [ToastContainerComponent],
    providers: [ToastContainerComponent],
})
export class ToastContainerModule { }
