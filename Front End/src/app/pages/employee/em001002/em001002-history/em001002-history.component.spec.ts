import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002HistoryComponent } from './em001002-history.component';

describe('Em001002HistoryComponent', () => {
  let component: Em001002HistoryComponent;
  let fixture: ComponentFixture<Em001002HistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002HistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002HistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
