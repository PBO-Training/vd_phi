import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Vm002002Component } from './vm002002.component';

describe('Vm002002Component', () => {
  let component: Vm002002Component;
  let fixture: ComponentFixture<Vm002002Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Vm002002Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Vm002002Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
