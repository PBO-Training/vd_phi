import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM005004Service {

  constructor(
    private http: HttpClient
  ) { }

  vm005004searchReport(body: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.ReportSummarySearch}`, body);
  }

  vm005004InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.InitReportSummarySearch}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

}
