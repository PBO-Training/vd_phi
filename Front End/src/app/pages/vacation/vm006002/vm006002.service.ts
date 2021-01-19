import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM006002Service {

  constructor(
    private http: HttpClient
  ) { }

  vm006002Create(ShiftWork: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendCreate}`, ShiftWork, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm006002InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendDetailInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm006002GetDetailShiftWork(id: number): Observable<any> {
    const body = {
      swID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendGetDetail}`, body);
  }

  vm006002UpdateShiftWork(ShiftWork: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendUpdate}`, ShiftWork, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm006002DeleteShiftWork(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { swID: id },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendDelete}`, httpOptions);
  }

}
