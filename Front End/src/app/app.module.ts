import { DatePipe } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbDateAdapter, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomAdapter, CustomDateParserFormatter } from './common/datepicker-format/NgbDateFRParserFormatter';
// import excel export service
import { ExportExcelService } from './services/export-excel/export-excel.service';
import { AuthInterceptorService } from './services/interceptor/interceptor-service';
import { TranslateLoaderModule } from './services/translate/translate-loader/translate-loader.module';
import { Translate } from './services/translate/translate.service';
/* layout */
import { ThemeLayoutModule } from './theme/layout/theme-layout.module';
import { SharedModule } from './theme/shared/shared.module';





export function initLanguage(translateService: Translate) {
  return (): Promise<any> => translateService.initLanguage();
}
@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SharedModule,
    ThemeLayoutModule,
    TranslateLoaderModule,
    HttpClientModule,
    NgSelectModule,
  ],
  providers: [
    { provide: APP_INITIALIZER, useFactory: initLanguage, multi: true, deps: [Translate] },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true },
    { provide: NgbDateAdapter, useClass: CustomAdapter },
    { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter },
    { provide: ExportExcelService, useClass: ExportExcelService },
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
