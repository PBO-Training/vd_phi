import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Degree } from './ms020002-entity';
@Injectable({
  providedIn: 'root'
})

export class MS020002Service {
  constructor(
    private http: HttpClient
  ) { }

  createDegree(body: Degree): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.DegreeApiUrl.DegreeCreate}`, body, { observe: 'response' });
  }

  updateDegree(body: Degree): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.DegreeApiUrl.DegreeUpdate}`, body, { observe: 'response' });
  }
  getDetailDegree(id: number): Observable<any> {
    const body = {
      degreeID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.DegreeApiUrl.DegreeGetDetail}`, body);
  }
}
