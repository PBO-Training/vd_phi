import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM006001Service {

  constructor(
    private http: HttpClient
  ) { }

  vm006001InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendSearchInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm006001searchShiftWork(body: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendSearch}`, body);
  }

}
