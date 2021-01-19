import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';

// Component
import { QuickModalComponent } from '../quick-modal/quick-modal.component';

@Component({
    selector: 'app-basic-screen',
    templateUrl: './basic-screen.component.html'
})
export class BasicComponent {
    constructor(
        private modalService: NgbModal,
        private translate: TranslateService,
    ) { }

    onOpenModal(): void {
        const modalRef = this.modalService.open(QuickModalComponent, {size: 'lg'});
        this.translate.get('confirm-message.update').subscribe(
            (text: string) => {
                modalRef.componentInstance.title = text;
            }
        );
    }
}
