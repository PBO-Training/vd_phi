import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { DepartmentRequest } from './ms002001-request';

@Injectable({
  providedIn: 'root'
})

export class MS002001SearchService {
  constructor(
    private http: HttpClient
  ) { }
  getDepartment(body: DepartmentRequest): Observable<any> {
    // TODO
      return this.http.post<any>(`${environment.apiUrl}${url.DepartmentApiUrl.DepartmentSearch}`,  body ).pipe(
        retry(1), // retry when failed request
      );
  }
  deleteDepartment(ids: any) {
    const httpOptions = {
     headers: new HttpHeaders({'Content-Type': 'application/json'}), body : { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.DepartmentApiUrl.DepartmentDelete}`, httpOptions );
  }
}
