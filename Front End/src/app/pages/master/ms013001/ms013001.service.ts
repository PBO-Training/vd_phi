import { retry } from 'rxjs/operators';
import { environment } from './../../../../environments/environment';
import { Observable } from 'rxjs';
import { HolidayRequest } from './ms013001-request';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class MS013001SearchService {

  constructor(private http: HttpClient) { }

  getHoliday(body: HolidayRequest): Observable<any> {
    // TODO
      return this.http.post<any>(`${environment.apiUrl}${url.HolidayApiUrl.HolidaySearch}`,  body ).pipe(
        retry(1), // retry when failed request
      );
  }

  deleteHoliday(ids: any) {
    const httpOptions = {
     headers: new HttpHeaders({'Content-Type': 'application/json'}), body : { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.HolidayApiUrl.HolidayDelete}`, httpOptions );
  }
}
