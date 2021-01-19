import { TestBed } from '@angular/core/testing';

import { Vm002002Service } from './vm002002.service';

describe('Vm002002Service', () => {
  let service: Vm002002Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Vm002002Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
