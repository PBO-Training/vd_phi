import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { ProjectPositionRequest } from './ms010001-request';

@Injectable({
  providedIn: 'root'
})
export class MS010001Service {

  constructor(private http: HttpClient) { }

  getPosition(body: ProjectPositionRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.ProjectPositionApiUrl.ProjectPositionSearch}`, body);
  }

  deletePosition(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.ProjectPositionApiUrl.ProjectPositionDelete}`, httpOptions);
  }
}
