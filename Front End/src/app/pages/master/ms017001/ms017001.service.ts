import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import * as url from '../../../common/constant/api-url';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Ms017001Service {

  constructor(private http: HttpClient) { }

  getRoleScreen(body): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.RoleScreenApiUrl.RoleScreenSearch}`, body);
  }
}
