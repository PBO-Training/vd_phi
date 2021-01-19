import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { StatusEmployeeRequest } from './ms008001-request';

@Injectable({
  providedIn: 'root'
})
export class MS008001Service {

  constructor(
    private http: HttpClient
  ) { }

  getStatusEmployee(body: StatusEmployeeRequest): Observable<any> {
    // TODO
    return this.http.post<any>(`${environment.apiUrl}${url.StatusEmployeeApiUrl.StatusEmployeeSearch}`, body).pipe(
      retry(1), // retry when failed request
    );
  }

  deleteStatusEmployee(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.StatusEmployeeApiUrl.StatusEmployeeDelete}`, httpOptions);
  }
}
