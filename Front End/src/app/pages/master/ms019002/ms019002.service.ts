import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { ScopeWork } from './ms019002-entity';

@Injectable({
  providedIn: 'root'
})
export class MS019002Service {

  constructor(private http: HttpClient) { }

  createScopeWork(body: ScopeWork): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.ScopeWorkApiUrl.ScopeWorkCreate}`, body, { observe: 'response' });
  }

  updateScopeWork(body: ScopeWork): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.ScopeWorkApiUrl.ScopeWorkUpdate}`, body,  { observe: 'response' });
  }
  getDetailScopeWork(id: number): Observable<any> {
    const body = {
      scopeWorkID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.ScopeWorkApiUrl.ScopeWorkGetDetail}`, body);
  }

}
