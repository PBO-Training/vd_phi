import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM001001Service {

  constructor(
    private http: HttpClient
  ) { }

  vm001001searchVacation(body: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationSendSearch}`, body);
  }

  vm001001InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationSendSearchInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

}
