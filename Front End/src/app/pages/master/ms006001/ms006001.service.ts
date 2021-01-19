import { retry } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { LevelLanguageRequest } from './ms006001-request';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class MS006001SearchService {

  constructor(
    private http: HttpClient
  ) { }
  getLevelLanguage(body: LevelLanguageRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.LevelLanguageApiUrl.LevelLanguageSearch}`, body).pipe(
      retry(1), // retry when failed request
    );
  }

  deleteLevelLanguage(ids: any) {
    const httpOptions = {
     headers: new HttpHeaders({'Content-Type': 'application/json'}), body : { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.LevelLanguageApiUrl.LevelLanguageDelete}`, httpOptions );
  }
}
