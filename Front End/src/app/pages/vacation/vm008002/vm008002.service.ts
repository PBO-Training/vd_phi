import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM008002Service {

  constructor(
    private http: HttpClient
  ) { }

  vm008002InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkSendDetailInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  vm008002GetDetail(swID: number, access: string): Observable<any> {
    const body = { 
      swID: swID,
      accessEmployeeID: access,
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkHistoryGetDetail}`, body);
  }

}
