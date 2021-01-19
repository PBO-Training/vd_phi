import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { ScopeWorkRequest } from './ms019001-request';

@Injectable({
  providedIn: 'root'
})
export class MS019001Service {

  constructor(private http: HttpClient) { }

  getScopeWork(body: ScopeWorkRequest): Observable<any> {
    // TODO
    return this.http.post<any>(`${environment.apiUrl}${url.ScopeWorkApiUrl.ScopeWorkSearch}`, body);
  }
  deleteScopeWork(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.ScopeWorkApiUrl.ScopeWorkDelete}`, httpOptions);
  }

}
