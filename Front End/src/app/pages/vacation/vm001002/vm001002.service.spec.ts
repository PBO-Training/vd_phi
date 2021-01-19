import { TestBed } from '@angular/core/testing';

import { Vm001002Service } from './vm001002.service';

describe('Vm001002Service', () => {
  let service: Vm001002Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Vm001002Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
