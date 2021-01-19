import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002PersonalComponent } from './em001002-personal.component';

describe('Em001002PersonalComponent', () => {
  let component: Em001002PersonalComponent;
  let fixture: ComponentFixture<Em001002PersonalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002PersonalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002PersonalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
