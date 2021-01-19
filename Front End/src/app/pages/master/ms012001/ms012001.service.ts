import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { VacationTypeRequest } from './ms012001-request';

@Injectable({
  providedIn: 'root'
})
export class MS012001Service {

  constructor(private http: HttpClient) { }

  getVacationType(body: VacationTypeRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationTypeApiUrl.VacationTypeSearch}`, body);
  }

  deleteVacationType(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.VacationTypeApiUrl.VacationTypeDelete}`, httpOptions);
  }
}
