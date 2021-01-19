import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002ContractComponent } from './em001002-contract.component';

describe('Em001002ContractComponent', () => {
  let component: Em001002ContractComponent;
  let fixture: ComponentFixture<Em001002ContractComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002ContractComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002ContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
