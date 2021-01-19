import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { EmployeePositionRequest } from './ms007001-request';

@Injectable({
  providedIn: 'root'
})
export class MS007001Service {

  constructor(private http: HttpClient) { }

  getPosition(body: EmployeePositionRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeePositionApiUrl.EmployeePositionSearch}`, body);
  }

  deletePosition(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.EmployeePositionApiUrl.EmployeePositionDelete}`, httpOptions);
  }
}
