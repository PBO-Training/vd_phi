import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {
  @Input() title: string;
  response: any;

  constructor(
    public activeModal: NgbActiveModal,
  ) { }

  ngOnInit(): void {
  }

  onSave() {
    this.activeModal.close('save');
  }

  onDismiss() {
    this.activeModal.close('dismiss');
  }

  onCloseModal = () => {
    this.activeModal.close('close');
  }
}
