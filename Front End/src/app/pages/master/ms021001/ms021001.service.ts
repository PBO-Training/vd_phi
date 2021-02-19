import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { ShiftWorkRequest } from './ms021001-request';

@Injectable({
  providedIn: 'root'
})
export class MS021001Service {

  constructor(private http: HttpClient) { }

  getAllShifwork(body: ShiftWorkRequest): Observable<any> {
    // TODO
    return this.http.post<any>(`${environment.apiUrl}${url.ShiftworkApiUrl.ShiftworkGetAll}`, body);
  }
  deleteShiftWork(ids: any) {

    return this.http.post<any>(`${environment.apiUrl}${url.ShiftworkApiUrl.ShiftworkDelete}`, ids);
  }
}


