import { Vm002002Request } from './vm002002-request';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Vm002002Service {

  constructor(
    private http: HttpClient
  ) { }  

  Vm002002InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationReceiveDetailInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  Vm002002getDetailVacation(id: number, access: string): Observable<any> {  
    const body = {
      vacationID : id,
      accessEmployeeID : access,      
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationReceiveGetDetail}`, body);
  }   

  Vm002002approveVacation(Vacation: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.VacationReceiveApprove}`, Vacation, {
     headers: new HttpHeaders()
     .set('Content-Type', 'application/json')
     });
 }

}
