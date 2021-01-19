import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { DegreeRequest } from './ms020001-request';

@Injectable({
  providedIn: 'root'
})
export class MS020001Service {

  constructor(private http: HttpClient) { }

  getDegree(body: DegreeRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.DegreeApiUrl.DegreeSearch}`, body);
  }

  deleteDegree(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.DegreeApiUrl.DegreeDelete}`, httpOptions);
  }
}
