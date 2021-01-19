import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM005002Service {

  constructor(
    private http: HttpClient
  ) { }

  vm005002InitList(id: number): Observable<any> {
    const body = {
      timeKeepingID : id,           
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.TimeKeepingInitImport}`, body);
  }

  vm005002processTimeKeeping(body: any): Observable<any> {
    const headers = { 'content-type': 'application/json'}  
    const bodySend=JSON.stringify({"timeKeepingDetail":body});
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.TimeKeepingProcessImport}`, bodySend, {'headers':headers});
  }

  vm005002saveTimeKeeping(body: any, id: Number): Observable<any> {
    const headers = { 'content-type': 'application/json'}  
    const bodySend=JSON.stringify({"timeKeepingDetail": body, "timekeepingID": id});
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.TimeKeepingSaveImport}`, bodySend, {'headers':headers});
  }
}
