import { Inject, Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import { Subject } from 'rxjs';
export const LANG_LIST = [
   { code: 'vi', display: 'VI', name: 'Tiếng việt'},
   { code: 'en', display: 'EN', name : 'English'},
   { code: 'jp', display: 'JP', name : 'Japanese'},
 ];
const LANG_DEFAULT = LANG_LIST[2];

const STORAGE_KEY = 'local_translate';
@Injectable({
  providedIn: 'root'
})
export class Translate  {
   // key that is used to access the data in local storageconst
  public currentlanguage$ = new Subject();
  constructor(
    private translate: TranslateService,
    @Inject(LOCAL_STORAGE) private storage: StorageService ) {
    }
   public initLanguage() {
   return new Promise<void>((resolve) => {
      this.translate.addLangs(LANG_LIST.map((lang) => lang.code));
      const language = this.getLanguage();
      if (language) {
         this.translate.setDefaultLang(language.code);
       } else {
         this.translate.setDefaultLang(LANG_DEFAULT.code);
       }
      this.setLanguage(language);
      resolve();
   });
   }
   public changeLanguage(code) {
      const lang = this._getFindLang(code);
      if (!lang || lang.code === this.translate.currentLang) {
         return;
       }
      this.storage.set(STORAGE_KEY, lang.code);
      this.setLanguage(lang);
   }
   private setLanguage(lang) {
      if (lang) {
         this.translate.use(lang.code);
         this.currentlanguage$.next(lang.code);
      }
   }
   private _getFindLang(code: string) {
      return code ? LANG_LIST.find((lang) => lang.code === code) : null;
   }
   private getLanguage() {
     let language = this._getFindLang(this.storage.get(STORAGE_KEY));
     if (language) {
        return language;
     }
     language = language || LANG_DEFAULT;
     this.storage.set(STORAGE_KEY, language.code);
     this.currentlanguage$.next(language.code);
     return language;
   }

   public getCurrentLanguage() {
      return this.storage.get(STORAGE_KEY);
   }
}

