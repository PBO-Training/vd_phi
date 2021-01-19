import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EMPTY, throwError } from 'rxjs';
import { catchError, debounceTime, finalize, map } from 'rxjs/operators';
import {
  ModalNotificationMaintenanceComponent
} from '../../theme/shared/components/modal-notification-maintenance/modal-notification-maintenance.component';
import { SpinerService } from '../../theme/shared/components/spinner/spiner.service';
import { StorageService } from '../storage/storage.service';


@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {
  modalRef: any;
  isShow: boolean;
  constructor(
    private tokenStorage: StorageService,
    private spinerService: SpinerService,
    private modalService: NgbModal,
    private router: Router,
  ) {
  }
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // TODO : FEATUTES LOGIN CONTINUTE
    let authReq = req;
    const authToken = this.tokenStorage.getToken();
    if (authToken != null) {
      authReq = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + authToken) });
    }
    // emit event open spiner
    this.spinerService.triggerSpiner(true);
    // send cloned request with header to the next handler.
    return next.handle(authReq)
      .pipe(
        map((resp: HttpEvent<any>) => {
          if (resp instanceof HttpResponse) {
            switch (resp.status) {
              case 201:
              // const modEvent = resp.clone({ body: { content: 'Succcess', error: null } });
              // return modEvent;
              // TODO: check status code
              default:
                this.isShow = false;
                break;
            }
          }
          return resp;
        }),
        catchError((error: HttpErrorResponse) => {
          switch (error.status) {
            case 400:
              // tslint:disable-next-line:max-line-length
              const invalidControl = (document.querySelectorAll('[formcontrolname="' + error.error.error.itemName + '"]')[0]) as HTMLElement;
              if (invalidControl) {
                const headerHeight = 56; /* PUT HEADER HEIGHT HERE */
                const buffer = 56; /* MAY NOT BE NEEDED */
                const topOfElement = window.pageYOffset + invalidControl.getBoundingClientRect().top - headerHeight - buffer;
                window.scroll({ top: topOfElement, behavior: 'smooth' });
                // formGroupInvalid.scrollIntoView({ behavior: 'smooth', block: 'start',  inline: 'nearest' });
                invalidControl.focus();
              }
              break;
            case 401:
              // refreshtoken or logout
              this.tokenStorage.logOut();
              this.router.navigate(['/login']);
              break;
            case 500:
              this.showPopup('notification.500');
              return EMPTY;
            case 504:
              this.showPopup('notification.504');
              return EMPTY;
            default:
              break;
          }
          return throwError(error);
        }),
        finalize(() => {
          // request completes, errors, or is cancelled
          this.spinerService.triggerSpiner(false);

        })
      );
  }
  showPopup(key: string) {
    if (!this.isShow) {
      this.isShow = true;
      // config backdrop, key pressed
      // tslint:disable-next-line: max-line-length
      this.modalRef = this.modalService.open(ModalNotificationMaintenanceComponent, { backdrop: 'static', keyboard: false, centered: true });
      this.modalRef.componentInstance.key = key;
      this.modalRef.result.then(
        (result) => {
          // close reload page
          // redirect home
          if (result === 'reload') {
            this.reload();
          } else {
            this.router.navigate(['/dashboard']);
          }
        },
        (reason) => {
        });
      this.modalRef.componentInstance.triggerShowPopup.subscribe(arg => this.isShow = arg);
    }
  }
  reload() {
    location.reload();
    //   this.router.routeReuseStrategy.shouldReuseRoute = () => {
    //     return false;
    //   };
    //   this.router.onSameUrlNavigation = 'reload';
    //   this.router.navigate([this.router.url]);
  }
}
