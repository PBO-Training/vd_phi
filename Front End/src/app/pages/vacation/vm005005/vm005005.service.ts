import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM005005Service {

  constructor(
    private http: HttpClient
  ) { }

  vm005005searchTrackingTimeKeeping(year: Number, employeeID: Number): Observable<any> {
    const body = {
      year : year,  
      employeeID: employeeID         
    };
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.TrackingTimeKeepingSearch}`, body);
  }

}
