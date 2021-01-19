import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { StatusEmployee } from './ms008002-entity';

@Injectable({
  providedIn: 'root'
})
export class MS008002Service {

  constructor(
    private http: HttpClient
  ) { }

  createStatusEmployee(body: StatusEmployee): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.StatusEmployeeApiUrl.StatusEmployeeCreate}`, body, { observe: 'response' });
  }

  updateStatusEmployee(body: StatusEmployee): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.StatusEmployeeApiUrl.StatusEmployeeUpdate}`, body, { observe: 'response' });
  }
  getDetailStatusEmployee(id: number): Observable<any> {
    const body = {
      statusEmployeeID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.StatusEmployeeApiUrl.StatusEmployeeGetDetail}`, body);
  }

}
