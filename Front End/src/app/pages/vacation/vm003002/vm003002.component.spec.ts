import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Vm003002Component } from './Vm003002.component';

describe('Vm003002Component', () => {
  let component: Vm003002Component;
  let fixture: ComponentFixture<Vm003002Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Vm003002Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Vm003002Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
