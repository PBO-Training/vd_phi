import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM008001Service {

  constructor(
    private http: HttpClient
  ) { }

  vm008001Search(body: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkHistorySearch}`, body);
  }

  vm008001InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ShiftWorkHistorySearchInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

}
