import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ClickOutsideModule } from 'ng-click-outside';
import { PerfectScrollbarConfigInterface, PerfectScrollbarModule, PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreabcrumbShareModule } from './components/breadcrumb-share/breabcrumb-share.module';
import { ToastContainerModule } from './components/toast-container/toast-container.module';
import { NgbCollapseModule, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { DebounceClickDirective } from './directives/debounce-click.directive';
import { SortTableDirective } from './directives/sort-table-header.directive';
import { ModalNotificationMaintenanceComponent
} from './components/modal-notification-maintenance/modal-notification-maintenance.component';
import { OnlyNumberDirective } from './directives/only-number.directive';
import { FocusDirective } from './directives/focus-first-el.directive';
import { AutoSizeDirective } from './directives/auto-size.directive';
import { SortTrTableDirective } from './directives/sort-table.directive';
import { SortThDirective } from './directives/sort-th.directive';
import { ProgessBarDirective } from './directives/progress-bar.directive';



/*import 'hammerjs';
import 'mousetrap';
import { GalleryModule } from '@ks89/angular-modal-gallery';*/

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

@NgModule({
  imports: [
    CommonModule,
    PerfectScrollbarModule,
    FormsModule,
    ReactiveFormsModule,
    BreabcrumbShareModule,
    ToastContainerModule,
    ClickOutsideModule,
    NgbPaginationModule,
    NgbCollapseModule,
    TranslateModule,
  ],
  exports: [
    CommonModule,
    PerfectScrollbarModule,
    FormsModule,
    ReactiveFormsModule,
    BreabcrumbShareModule,
    ToastContainerModule,
    ClickOutsideModule,
    SpinnerComponent,
    NgbPaginationModule,
    NgbCollapseModule,
    TranslateModule,
    DebounceClickDirective,
    SortTableDirective,
    OnlyNumberDirective,
    FocusDirective,
    AutoSizeDirective,
    SortTrTableDirective,
    SortThDirective,
    ProgessBarDirective

  ],
  declarations: [
    SpinnerComponent,
    DebounceClickDirective,
    SortTableDirective,
    ModalNotificationMaintenanceComponent,
    OnlyNumberDirective,
    FocusDirective,
    AutoSizeDirective,
    SortTrTableDirective,
    SortThDirective,
    ProgessBarDirective
  ],
  entryComponents: [
    ModalNotificationMaintenanceComponent,
  ],
  providers: [
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    },
  ]
})
export class SharedModule { }
