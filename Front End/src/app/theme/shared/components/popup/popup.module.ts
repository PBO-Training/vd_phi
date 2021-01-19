import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { PopupComponent } from './popup.component';
import { TranslateModule } from '@ngx-translate/core';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule
    ],
    exports: [PopupComponent, CommonModule],
    declarations: [PopupComponent],
    providers: [PopupComponent]
})
export class PopupModule { }
