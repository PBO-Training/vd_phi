
import { Injectable } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import * as _ from 'lodash';
import { BehaviorSubject, combineLatest, Observable, Subject, Subscription } from 'rxjs';
import { debounceTime, delay, map, shareReplay, startWith, takeUntil } from 'rxjs/operators';

@Injectable()
export class StoreService {
  store = new Subject();
  store$ = this.store.asObservable();
  valueChanges = new Subject();
  valueChanges$ = this.valueChanges.asObservable();
  subscription$ = new Subject();
  isDirty$: Observable<boolean>;
  isSubmit = new Subject();
  isSubmit$ = this.isSubmit.asObservable();
  isValidSubmit = new Subject();
  isValidSubmit$ = this.isValidSubmit.asObservable().pipe(startWith(true));
  subscription: Subscription;
  isSubmitFormSkill = new Subject();
  isEditFormSkill = new Subject();
  constructor() {
  }

  updateStatusForm(form: FormGroup | FormArray) {
    for (const iterator of Object.keys(form.controls)) {
      const abstractControl = form.controls[iterator];
      if (abstractControl instanceof FormGroup || abstractControl instanceof FormArray) {
        if (abstractControl.errors !== null) {
          this.isValidSubmit.next(false);
          return true;
        }
        const key = this.updateStatusForm(abstractControl);
        // return once got true
        if (key) {
          return true;
        }
      } else {
        abstractControl.updateValueAndValidity({ onlySelf: true, emitEvent: false });
        this.subscription = this.isSubmit$.subscribe(val => {
          abstractControl.pristine = false;
        });
        if (abstractControl.status === 'INVALID' || abstractControl.errors !== null) {
          this.isValidSubmit.next(false);
          return true;
        } else {
          this.isValidSubmit.next(true);
        }
      }
    }
  }

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
      debounceTime(300),
      map(([store, valueChanges]) => _.isEqual(store, valueChanges) === false),
      startWith(false),
      shareReplay({ bufferSize: 1, refCount: true })
    );
    return isDirty$;
  }
}

