import { Component, EventEmitter, OnInit, Input, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {  } from 'protractor';

@Component({
  selector: 'app-modal-confirm',
  templateUrl: './modal-confirm.component.html',
  styleUrls: ['./modal-confirm.component.scss']
})
export class ModalConfirmComponent implements OnInit {
  @Input() title: string;
  @Input() saveTab: number;
  @Input() swapTab = false;
  @Input() isDelete = true;
  @Input() isBack = false;
  @Input() backgroundColor: string;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  constructor(
    public activeModal: NgbActiveModal,

  ) { }
  ngOnInit(): void {
  }
  save() {
    this.submitForm.next(true);
    const formGroupInvalid = document.querySelectorAll('.is-invalid')[0] as HTMLElement;
    if (formGroupInvalid) {
        const headerHeight = 56; /* PUT HEADER HEIGHT HERE */
        const buffer = 56; /* MAY NOT BE NEEDED */
        const topOfElement = window.pageYOffset + formGroupInvalid.getBoundingClientRect().top - headerHeight - buffer;
        window.scroll({ top: topOfElement, behavior: 'smooth' });
    }
    this.activeModal.close('save tab');
  }
  deleteConfirm() {
    this.activeModal.close('delete');
  }
}
