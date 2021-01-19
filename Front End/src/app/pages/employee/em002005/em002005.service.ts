import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { ChangePasswordRequest } from './em002005-request';

@Injectable({
  providedIn: 'root'
})
export class EM002005Service {

  constructor(
    private http: HttpClient
  ) { }

  updatePassword(body: ChangePasswordRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeUpdatePassword}`, body, { observe: 'response' });
  }

}
