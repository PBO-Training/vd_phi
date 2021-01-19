import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { LanguageRequest } from './ms005001-request';

@Injectable({
  providedIn: 'root'
})
export class MS005001SearchService {

  constructor(private http: HttpClient) { }

  getLanguage(body: LanguageRequest): Observable<any> {
    // TODO
    return this.http.post<any>(`${environment.apiUrl}${url.LanguageApiUrl.LanguageSearch}`, body);
  }
  deleteLanguage(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.LanguageApiUrl.LanguageDelete}`, httpOptions);
  }
}
