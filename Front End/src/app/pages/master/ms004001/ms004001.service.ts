import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { LevelSkillRequest } from './ms004001-request';

@Injectable({
  providedIn: 'root'
})
export class MS004001Service {

  constructor(
    private http: HttpClient
  ) { }
  getLevelSkill(body: LevelSkillRequest): Observable<any> {
    // TODO
    return this.http.post<any>(`${environment.apiUrl}${url.LevelSkillApiUrl.LevelSkillSearch}`, body);
  }
  deleteLevelSkill(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.LevelSkillApiUrl.LevelSkillDelete}`, httpOptions);
  }

}
