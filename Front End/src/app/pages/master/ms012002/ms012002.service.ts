import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { VacationType } from './ms012002-entity';
@Injectable({
  providedIn: 'root'
})

export class MS012002Service {
  constructor(
    private http: HttpClient
  ) { }

  createVacationType(body: VacationType): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationTypeApiUrl.VacationTypeCreate}`, body, { observe: 'response' });
  }

  updateVacationType(body: VacationType): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.VacationTypeApiUrl.VacationTypeUpdate}`, body, { observe: 'response' });
  }
  getDetailVacationType(id: number): Observable<any> {
    const body = {
      vacationTypeID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationTypeApiUrl.VacationTypeGetDetail}`, body);
  }
}
