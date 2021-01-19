import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { RoleRequest } from './ms018001-request';

@Injectable({
  providedIn: 'root'
})
export class MS018001Service {

  constructor(private http: HttpClient) { }

  getRole(body: RoleRequest): Observable<any> {
    // TODO
    return this.http.post<any>(`${environment.apiUrl}${url.RoleApiUrl.RoleSearch}`, body);
  }
  deleteRole(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.RoleApiUrl.RoleDelete}`, httpOptions);
  }

}
