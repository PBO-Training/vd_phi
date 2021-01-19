import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { UserRequest } from './ms001001-request';
import { Role } from './ms001001-role-entity';

@Injectable({
  providedIn: 'root'
})

export class MS001001Service {
  constructor(
    private http: HttpClient
  ) { }
  getUser(body: UserRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.UserApiUrl.UserSearch}`, body);
  }

  deleteListUser(ids: number[]): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids, companyID: 1 }
    };
    return this.http.delete(`${environment.apiUrl}${url.UserApiUrl.UserDelete}`, httpOptions);
  }

  initDropDownList(body: Role): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.UserApiUrl.UserInit}`, body);
  }

}
