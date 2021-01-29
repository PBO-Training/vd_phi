import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-mstest',
  templateUrl: './mstest.component.html',
  styleUrls: ['./mstest.component.scss']
})
export class MStestComponent implements OnInit {
  @Input() item;
  @Output() newItemEvent = new EventEmitter();
  constructor() { }

  ngOnInit(): void {
  }
  addNewItem(){
    this.newItemEvent.emit(this.item);
  }
}
