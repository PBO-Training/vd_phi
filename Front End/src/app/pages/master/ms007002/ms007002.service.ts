import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { EmployeePosition } from './ms007002-entity';

@Injectable({
  providedIn: 'root'
})
export class MS007002Service {

  constructor(private http: HttpClient) { }

  createPosition(body: EmployeePosition): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeePositionApiUrl.EmployeePositionCreate}`, body, { observe: 'response' });
  }
  updatePosition(body: EmployeePosition): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.EmployeePositionApiUrl.EmployeePositionUpdate}`, body, { observe: 'response' });
  }
  getDetailPosition(id: number): Observable<any> {
    const body = {
      positionEmployeeID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeePositionApiUrl.EmployeePositionGetDetail}`, body);
  }
}
