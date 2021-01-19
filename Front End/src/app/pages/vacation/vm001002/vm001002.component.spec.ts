import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Vm001002Component } from './vm001002.component';

describe('Vm001002Component', () => {
  let component: Vm001002Component;
  let fixture: ComponentFixture<Vm001002Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Vm001002Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Vm001002Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
