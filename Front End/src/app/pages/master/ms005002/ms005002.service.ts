import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Language } from './ms005002-entity';

@Injectable({
  providedIn: 'root'
})
export class MS005002DetailService {

  constructor(private http: HttpClient) { }

  createLanguage(body: Language): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.LanguageApiUrl.LanguageCreate}`, body, { observe: 'response' });
  }

  updateLanguage(body: Language): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.LanguageApiUrl.LanguageUpdate}`, body,  { observe: 'response' });
  }
  getDetailLanguage(id: number): Observable<any> {
    const body = {
      languageID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.LanguageApiUrl.LanguageGetDetail}`, body);
  }
}
