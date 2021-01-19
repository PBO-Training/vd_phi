import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';
import { Customer } from './ms009002-entity';
@Injectable({
    providedIn: 'root'
})

export class MS009002DetailService {
    constructor(
        private http: HttpClient
    ) { }

    /*
        Call Api create customer
        @param body - Type is Customer
    */
    createCustomer(body: Customer): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.CustomerApiUrl.CustomerCreate}`, body, { observe: 'response' });
    }

    /*
        Call Api update customer
        @param body - Type is Customer
    */
    updateCustomer(body: Customer): Observable<any> {
        return this.http.put<any>(`${environment.apiUrl}${url.CustomerApiUrl.CustomerUpdate}`, body,  { observe: 'response' });
    }

    /*
        Call Api get detail customer
        @param body - Type is Customer
    */
    getDetailCustomer(id: number): Observable<any> {
        const body = {
            customerID: id
        };
        return this.http.post<any>(`${environment.apiUrl}${url.CustomerApiUrl.CustomerGetDetail}`, body);
    }
}
