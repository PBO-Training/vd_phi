import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002HistoryViewComponent } from './em001002-history-view.component';

describe('Em001002HistoryViewComponent', () => {
  let component: Em001002HistoryViewComponent;
  let fixture: ComponentFixture<Em001002HistoryViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002HistoryViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002HistoryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
