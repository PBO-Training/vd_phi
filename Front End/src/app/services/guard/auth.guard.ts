import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../storage/storage.service';


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    role: any;
    currentLink: string;
    constructor(
        private router: Router,
        private storageService: StorageService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const currentUrl = route.routeConfig.path;
        if (currentUrl !== 'project/detail/:id') {
            this.storageService.removeProjectID();
        }
        if (currentUrl !== 'login' && !this.storageService.getToken()) {
            this.router.navigateByUrl('/login');
            return false;
        }
        if (currentUrl === 'login' && this.storageService.getToken()) {
            this.router.navigateByUrl('/dashboard');
            return false;
        }
        if (currentUrl === 'login' && !this.storageService.getToken()) {
            return true;
        }
        if (currentUrl === 'profile') {
            const user: any = this.storageService.getUser();
            this.currentLink = '/profile/detail/' + user.employeeID;
            this.router.navigateByUrl(this.currentLink);
            return false;
        }
        this.role = this.storageService.getRole();
        if (this.role && this.getAccessScreen(this.role.listScreen, currentUrl) === true) {
            return true;
        }
        this.router.navigateByUrl('/403');
        return false;
    }

    getAccessScreen(listScreen: any, screenUrl: string): boolean {
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < listScreen.length; i++) {
            if (listScreen[i].screenUrl === screenUrl) {
                return listScreen[i].access;
            }
        }
        return false;
    }
}
