import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ScreenAction } from './../../common/screen-action/screen-action';
import { StorageService } from '../../services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthActionService {

  currentLink: string;
  routerLink: string;
  url: any[] = [];

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  initAuthAction(screenAction: ScreenAction) {
    this.routerLink = '';
    this.currentLink = this.router.url.substring(1);
    this.url = this.currentLink.split('/');

    if (this.url.length > 2) { // truong hop vao page detail
      this.url.pop();
      this.url.splice(1, 0, '/');
      this.url.forEach(val => {
        if (val !== undefined && val !== null) {
          this.routerLink += val;
        }
      });
      this.routerLink += '/:id'; // cong vao cho giong link trong DB
    } else if (this.url.length > 1) { // truong hop vao page create
      this.routerLink = this.url[0] + '/' + this.url[1];
    } else { // truong hop vao page list
      this.routerLink = this.url[0];
    }

    const role: any = this.storageService.getRole();
    if (role && role.listScreen) {
      role.listScreen.forEach(screen => {
        if (screen.screenUrl === this.routerLink && screen.listAction.length) {
          screen.listAction.forEach(action => {
            screenAction[action.actionCode] = action.access;
          });
        }
      });
    }
    return screenAction;
  }
}
