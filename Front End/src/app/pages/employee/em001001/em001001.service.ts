import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class EM001001SearchService {

  constructor(
    private http: HttpClient
  ) { }
  em001001Search(Employee: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeSearch}`, Employee);
  }

  em001001InitList(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeSearchInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  em001001Delete(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listID: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeDelete}`, httpOptions);
  }

  em001001Export(request: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeExport}`, request);
  }
}
