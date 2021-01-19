import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { ProjectPosition } from './ms010002-entity';
@Injectable({
  providedIn: 'root'
})

export class MS010002Service {
  constructor(
    private http: HttpClient
  ) { }

  createPosition(body: ProjectPosition): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.ProjectPositionApiUrl.ProjectPositionCreate}`, body, { observe: 'response' });
  }

  updatePosition(body: ProjectPosition): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}${url.ProjectPositionApiUrl.ProjectPositionUpdate}`, body, { observe: 'response' });
  }
  getDetailPosition(id: number): Observable<any> {
    const body = {
      positionProjectID: id
    };
    return this.http.post<any>(`${environment.apiUrl}${url.ProjectPositionApiUrl.ProjectPositionGetDetail}`, body);
  }
}
