import { environment } from './../../../../environments/environment';
import { Observable } from 'rxjs';
import { Holiday } from './ms013002-entity';
import { HttpClient } from '@angular/common/http';
import * as url from '../../../common/constant/api-url';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MS013002DetailService {

  constructor( private http: HttpClient ) { }

  createHoliday(body: {}): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.HolidayApiUrl.HolidayCreate}`, body , { observe: 'response' });
  }

  updateHoliday(body: {}): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.HolidayApiUrl.HolidayUpdate}`, body,  { observe: 'response' });
  }
  getDetailHoliday(id: number): Observable<any> {
    const body = {
      holidayID : id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.HolidayApiUrl.HolidayGetDetail}`, body);
  }
}
