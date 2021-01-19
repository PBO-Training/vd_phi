import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
@Injectable({
    providedIn: 'root'
})
export class MS999001Service {
    constructor(
        private http: HttpClient
    ) { }

    initDashboard(): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.DashboardApiUrl.StatisticEmployee}`, {});
    }
}
