import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM005003Service {

  constructor(
    private http: HttpClient
  ) { }

  vm005003searchTimeKeepingDetail(body: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.TimeKeepingSearchDetail}`, body);
  } 
  
  deleteViolation(ids: any, timekeepingID: Number) {
    const httpOptions = {
     headers: new HttpHeaders({'Content-Type': 'application/json'}), body : { listID: ids,  timekeepingID: timekeepingID},
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.VacationApiUrl.TimeKeepingSearchDelete}`, httpOptions );
  }
}
