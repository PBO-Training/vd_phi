import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({
  providedIn: 'root'
})
export class VM005001Service {

  constructor(
    private http: HttpClient
  ) { }

  vm005001searchTimeKeeping(body: any): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.VacationApiUrl.TimeKeepingSearch}`, body);
  }  

  resetTimeKeeping(id: any) {
    const httpOptions = {
     headers: new HttpHeaders({'Content-Type': 'application/json'}), body : { timekeepingID: id },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.VacationApiUrl.TimeKeepingReset}`, httpOptions );
  }
}
