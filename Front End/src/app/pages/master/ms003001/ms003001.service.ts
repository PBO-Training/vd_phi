import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { SkillRequest } from './ms003001-request';

@Injectable({
  providedIn: 'root'
})
export class MS003001Service {

  constructor(private http: HttpClient) { }

  getSkill(body: SkillRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.SkillApiUrl.SkillSearch}`, body);
  }

  deleteSkill(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.SkillApiUrl.SkillDelete}`, httpOptions);
  }

  ms0030001Init(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.SkillApiUrl.SkillInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }
}
