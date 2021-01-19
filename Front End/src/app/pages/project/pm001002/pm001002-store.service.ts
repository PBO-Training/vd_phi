import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import * as url from '../../../common/constant/api-url';


@Injectable({providedIn: 'root'})
export class Pm001002StoreSerivce {
    constructor() { }

    tabMemberDetailStore = new Subject();

    onShowDetailMember(isShowDetailMember): void {
        this.tabMemberDetailStore.next(isShowDetailMember);
    }
}
