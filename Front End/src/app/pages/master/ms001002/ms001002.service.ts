import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { User } from './ms001002-user-entity';

@Injectable({
  providedIn: 'root'
})
export class MS001002Service {

  constructor(
    private http: HttpClient
  ) { }

  createUser(body: User): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.UserApiUrl.UserCreate}`, body, { observe: 'response' });
  }

  updateUser(body: User): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.UserApiUrl.UserUpdate}`, body, { observe: 'response' });
  }

  getDetailUser(id: number): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.UserApiUrl.UserGetDetail}`, {
      userID: id
    });
  }

  initDetail(): Observable<any> {
    return this.http.post<any[]>(`${environment.apiUrl}${url.UserApiUrl.UserInitDetails}`, {
      headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
    });
  }

  generateEmp() {
    return this.http.post<any>(`${environment.apiUrl}${url.UserApiUrl.EmployeeCreateDefault}`, { observe: 'response' });
  }
}
