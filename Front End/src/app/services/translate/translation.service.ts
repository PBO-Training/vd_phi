import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable, EMPTY } from 'rxjs';
import { Translate } from './translate.service';

@Injectable({ providedIn: 'root' })
export class TranslationService {

    constructor(
        private translateService: TranslateService,
        private translate: Translate
    ) { }
    getTranslation(key: string): Observable<any> {
        if (key) {
            const codeError = key.slice(10, 13);
            return this.translateService.get(`server-error.${codeError}`);
        }
        return EMPTY;
    }
    changeTranslatetion(): Observable<any> {
        return this.translate.currentlanguage$;
    }
    getErrorsChangeTranslation(key: string): Observable<any> {
        if (key) {
            const keyTranslation = key.slice(10, 13);
            return this.translateService.get(`server-error.${keyTranslation}`);
        }
        return EMPTY;

    }

    convertToKeyJson(key: string): string {
        const codeError = key.slice(10, 13);
        return `server-error.${codeError}`;
    }
}
