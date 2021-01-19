import { Vm003002Request } from './Vm003002-request';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Vm003002Service {

  constructor(
    private http: HttpClient
  ) { }  

  Vm003002InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationHistoryDetailInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  Vm003002getHistoryDetailVacation(id: number, access: string): Observable<any> {  
    const body = {
      vacationID : id,
      accessEmployeeID : access,      
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationHistoryGetDetail}`, body);
  }   

  /*Vm003002approveVacation(Vacation: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationApprove}`, Vacation, {
     headers: new HttpHeaders()
     .set('Content-Type', 'application/json')
     });
 }*/
 
}
