import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Department } from './ms002002-entity';
@Injectable({
  providedIn: 'root'
})

export class MS002002Service {
  constructor(
    private http: HttpClient
  ) { }

  createDepartment(body: Department): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.DepartmentApiUrl.DepartmentCreate}`, body , { observe: 'response' });
  }

  updateDepartment(body: Department): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.DepartmentApiUrl.DepartmentUpdate}`, body,  { observe: 'response' });
  }
  getDetailDepartment(id: number): Observable<any> {
    const body = {
      departmentID : id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.DepartmentApiUrl.DepartmentGetDetail}`, body);
  }
}
