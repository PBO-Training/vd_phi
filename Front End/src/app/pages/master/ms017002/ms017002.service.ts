import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import * as url from '../../../common/constant/api-url';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MS017002Service {

  constructor(private http: HttpClient) { }

  getRoleScreen(body): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.RoleScreenApiUrl.RoleScreenSearch}`, body);
  }

  ms017002Init(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.RoleScreenApiUrl.RoleScreenDetailInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  ms017002GetDetail(data): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.RoleScreenApiUrl.RoleScreenGetDetail}`, data);
  }

  ms017002Update(data): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.RoleScreenApiUrl.RoleScreenUpdate}`, data,  { observe: 'response' });
  }
}
