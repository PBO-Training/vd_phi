import { TestBed } from '@angular/core/testing';

import { Vm003002Service } from './Vm003002.service';

describe('Vm003002Service', () => {
  let service: Vm003002Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Vm003002Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
