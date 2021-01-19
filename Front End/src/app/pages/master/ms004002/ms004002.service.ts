import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { LevelSkill } from './ms004002-entity';

@Injectable({
  providedIn: 'root'
})
export class MS004002Service {

  constructor(
    private http: HttpClient
  ) { }

  createLevelSkill(body: LevelSkill): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.LevelSkillApiUrl.LevelSkillCreate}`, body, { observe: 'response' });
  }

  updateLevelSkill(body: LevelSkill): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.LevelSkillApiUrl.LevelSkillUpdate}`, body ,  { observe: 'response' });
  }
  getDetailLevelSkill(id: number): Observable<any> {
    const body = {
      levelSkillID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.LevelSkillApiUrl.LevelSkillGetDetail}`, body);
  }

}
