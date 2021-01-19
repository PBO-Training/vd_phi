import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StoreSystemService {

  avataSubject = new BehaviorSubject(null);
  fullNameSubject = new BehaviorSubject(null);

  constructor() {}

  clearStore(){
    this.avataSubject.next(null);
    this.fullNameSubject.next(null);
    // this.avataSubject.complete();
    // this.fullNameSubject.complete();
  }

  setAvataSubject(value){
    this.avataSubject.next(value);
  }

  setFullNameSubject(value){
    this.fullNameSubject.next(value);
  }
}
