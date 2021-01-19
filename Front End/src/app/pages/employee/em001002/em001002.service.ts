import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class Em001002Service {

  constructor(
    private http: HttpClient
  ) { }
  em001002create(Employee: {}): Observable<any> {
     return this.http.post<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeCreate}`, Employee, {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
      });
  }
  em001002update(Employee: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeUpdate}`, Employee, {
     headers: new HttpHeaders()
     .set('Content-Type', 'application/json')
     });
 }
  em001002init(): Observable<any> {
    return this.http.post<any[]>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeInit}`, {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
    });
  }
  em001002details(employeeID: any): Observable<any> {
    return this.http.post<any[]>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeDetails}`, employeeID, {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
    });
  }
}
