import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001001ModalComponent } from './em001001-modal.component';

describe('Em001001ModalComponent', () => {
  let component: Em001001ModalComponent;
  let fixture: ComponentFixture<Em001001ModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001001ModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001001ModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
