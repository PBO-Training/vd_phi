import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002ContractViewComponent } from './em001002-contract-view.component';

describe('Em001002ContractViewComponent', () => {
  let component: Em001002ContractViewComponent;
  let fixture: ComponentFixture<Em001002ContractViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002ContractViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002ContractViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
