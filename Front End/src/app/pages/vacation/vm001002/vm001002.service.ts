import { Vm001002Request } from './vm001002-request';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Vm001002Service {

  constructor(
    private http: HttpClient
  ) { }

  vm001002create(Vacation: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationSendCreate}`, Vacation, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm001002InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationSendDetailInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm001002getDetailVacation(id: number): Observable<any> {
    const body = {
      vacationID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationSendGetDetail}`, body);
  }

  vm001002updateVacation(Vacation: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationSendUpdate}`, Vacation, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm001002deleteVacation(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { vacationID: id },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationSendDelete}`, httpOptions);
  }

}
