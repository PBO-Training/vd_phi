import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class MS021002Service {

  constructor(private http: HttpClient) { }
  getDetailShifwork(body: any): Observable<any> {
    // TODO
    return this.http.post<any>(`${environment.apiUrl}${url.ShiftworkApiUrl.ShiftworkGetDetail}`, body);
  }
  updateShiftwork(body:any): Observable<any>{
    return this.http.post<any>(`${environment.apiUrl}${url.ShiftworkApiUrl.ShiftworkUpdate}`, body);
  }
  createShiftwork(body:any): Observable<any>{
    return this.http.post<any>(`${environment.apiUrl}${url.ShiftworkApiUrl.ShiftworkCreate}`, body);
  }
  initStepBreakTime(body:any):Observable<any>{
    return this.http.post<any>(`${environment.apiUrl}${url.ShiftworkApiUrl.ShiftworkInitBreakTime}`, body);
  }
  getTimeWorkSystem(body:any):Observable<any>{
    return this.http.post<any>(`${environment.apiUrl}${url.ShiftworkApiUrl.ShiftworkGetTimeWorkSystem}`, body);
  }
  ///

}

