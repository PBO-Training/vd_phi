import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { CustomerRequest } from './ms009001-request';

@Injectable({
  providedIn: 'root'
})
export class MS009001SearchService {
  constructor(
    private http: HttpClient
  ) { }
  // Get all customer or search customer with body contain customerName or customerCode
  getCustomer(body: CustomerRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.CustomerApiUrl.CustomerSearch}`, body);
  }
  // Delete a customer or many customer
  deleteCustomer(ids: any) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
    };
    return this.http.delete<any>(`${environment.apiUrl}${url.CustomerApiUrl.CustomerDelete}`, httpOptions);
  }
}
