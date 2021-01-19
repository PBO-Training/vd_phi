import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Skill } from './ms003002-entity';

@Injectable({
  providedIn: 'root'
})
export class MS003002Service {

  constructor(private http: HttpClient) { }

  createSkill(body: Skill): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.SkillApiUrl.SkillCreate}`, body, { observe: 'response' });
  }
  updateSkill(body: Skill): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.SkillApiUrl.SkillUpdate}`, body, { observe: 'response' });
  }
  getDetailSkill(id: number): Observable<any> {
    const body = {
      skillID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.SkillApiUrl.SkillGetDetail}`, body);
  }
  ms0030002Init(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.SkillApiUrl.SkillTypeInit}`, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }
}
