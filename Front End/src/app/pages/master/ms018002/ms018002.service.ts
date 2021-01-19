import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Role } from './ms018002-entity';

@Injectable({
  providedIn: 'root'
})
export class MS018002Service {

  constructor(private http: HttpClient) { }

  createRole(body: Role): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.RoleApiUrl.RoleCreate}`, body, { observe: 'response' });
  }

  updateRole(body: Role): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.RoleApiUrl.RoleUpdate}`, body,  { observe: 'response' });
  }
  getDetailRole(id: number): Observable<any> {
    const body = {
      roleID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.RoleApiUrl.RoleGetDetail}`, body);
  }

}
