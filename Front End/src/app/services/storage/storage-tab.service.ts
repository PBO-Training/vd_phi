
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { BehaviorSubject, combineLatest, Observable, Subject } from 'rxjs';
import { debounceTime, map, shareReplay, startWith } from 'rxjs/operators';

@Injectable()
export class StoreTabService {
  store = new Subject();
  store$ = this.store.asObservable();
  valueChanges = new Subject();
  valueChanges$ = this.valueChanges.asObservable();
  detect = new Subject();
  detect$ = this.detect.asObservable();
  isDirty$: Observable<boolean>;

  scrollFlag = new BehaviorSubject(false);
  scrollFlag$ = this.scrollFlag.asObservable();
  constructor() { }
  saveStore(formValue: any) {
    this.store.next(JSON.stringify(formValue));
  }
  clearStore() {
    this.store.next();
    this.store.complete();
  }
  clearValueChange() {
    this.valueChanges.next();
    this.store.complete();
  }
  saveValueChange(formValue: any) {
    this.valueChanges.next(JSON.stringify(formValue));
  }
  dirtyCheck(): Observable<boolean> {
    const store$ = this.store$;
    const value$ = this.valueChanges$;
    const isDirty$ = combineLatest([
      store$,
      value$
    ]).pipe(
      map(([store, valueChanges]) => _.isEqual(store, valueChanges) === false),
      startWith(false),
      shareReplay({ bufferSize: 1, refCount: true })
    );
    return isDirty$;
  }
}

