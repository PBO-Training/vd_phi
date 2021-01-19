import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002PersonalViewComponent } from './em001002-personal-view.component';

describe('Em001002PersonalViewComponent', () => {
  let component: Em001002PersonalViewComponent;
  let fixture: ComponentFixture<Em001002PersonalViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002PersonalViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002PersonalViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
