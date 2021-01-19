import { NgModule } from '@angular/core';
import { ModalConfirmComponent } from './modal-confirm.component';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule
    ],
    exports: [ModalConfirmComponent, CommonModule],
    declarations: [ModalConfirmComponent],
    providers: [ModalConfirmComponent,
    ]
})
export class ModalConfirmModule { }
