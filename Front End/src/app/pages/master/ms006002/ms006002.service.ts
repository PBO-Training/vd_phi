import { Observable } from 'rxjs';
import { LevelLanguage } from './../ms006001/ms006001-entity';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as url from '../../../common/constant/api-url';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MS006002DetailService {

  constructor(private http: HttpClient) { }

  createLevelLanguage(body: LevelLanguage): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.LevelLanguageApiUrl.LevelLanguageCreate}`, body, { observe: 'response' });
  }

  updateLevelLanguage(body: LevelLanguage): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.LevelLanguageApiUrl.LevelLanguageUpdate}`, body, { observe: 'response' });
  }

  getDetailLevelLanguage(id: number): Observable<any> {
    const body = {
      levelLanguageID : id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.LevelLanguageApiUrl.LevelLanguageGetDetail}`, body);
  }
}
