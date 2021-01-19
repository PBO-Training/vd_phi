import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM003001Service {

  constructor(
    private http: HttpClient
  ) { }

  vm003001searchVacation(body: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationHistorySearch}`, body);
  }

  vm003001InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationHistorySearchInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

}
