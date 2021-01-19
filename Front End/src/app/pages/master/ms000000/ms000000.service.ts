import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthApiUrl } from '../../../common/constant/api-url';
import { environment } from '../../../../environments/environment';
import { LoginRequest } from './ms000000-request';

@Injectable({
  providedIn: 'root'
})
export class Ms000000Service {

  constructor(private http: HttpClient) { }

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${AuthApiUrl.login}`, {
      username: credentials.username,
      password: credentials.password,
      companyCode: credentials.companyCode,
    });
  }
}
