import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class Pm001001SearchService {

  constructor(private http: HttpClient) { }
  pm0010001GetList(object: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.ProjectSearch}`, object);
  }

  pm0010001Delete(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.ProjectApiUrl.ProjectDelete}`, httpOptions);
  }

  pm0010001Init(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.ProjectSearchInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }
}
