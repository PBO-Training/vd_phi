import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class SpinerService {

    private subject = new Subject<any>();

    constructor() { }
    triggerSpiner(status: boolean) {
        this.subject.next(status);
    }
    onSpiner(): Observable<any> {
        return this.subject.asObservable();
    }
}
