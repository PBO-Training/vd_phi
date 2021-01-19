import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-quick-modal',
    templateUrl: './quick-modal.component.html'
})
export class QuickModalComponent {
    @Input() title;

    constructor(public activeModal: NgbActiveModal) { }
}
