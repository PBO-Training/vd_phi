import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NgbActiveModal, NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-notification-maintenance',
  templateUrl: './modal-notification-maintenance.component.html',
  styleUrls: ['./modal-notification-maintenance.component.scss'],

})
export class ModalNotificationMaintenanceComponent implements OnInit {
  @Input() key: string;
  @Input() className: string;
  @Output() triggerShowPopup = new EventEmitter();
  constructor(
    public activeModal: NgbActiveModal,
  ) {
  }
  ngOnInit(): void {
  }
  close(action: string) {
    // if (action === 'reload') {
    this.activeModal.close(action);
    // }
    this.triggerShowPopup.emit(false);
  }
}


