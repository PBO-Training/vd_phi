import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbDropdownConfig } from '@ng-bootstrap/ng-bootstrap';
import { StoreSystemService } from '../../../../../services/store/store-system.service';
import { StorageService } from '../../../../../services/storage/storage.service';
import { LANG_LIST, Translate } from '../../../../../services/translate/translate.service';

@Component({
  selector: 'app-nav-right',
  templateUrl: './nav-right.component.html',
  styleUrls: ['./nav-right.component.scss'],
  providers: [NgbDropdownConfig]
})
export class NavRightComponent implements OnInit, OnDestroy {
  userID: string;
  currentLanguage: string;
  langList = LANG_LIST;
  user: any;
  public showInitials = false;
  public circleColor: string;
  public initials: string;
  public fullName: string;
  private colors = [
    '#EB7181', // red
    '#468547', // green
    '#FFD558', // yellow
    '#3670B2', // blue
    '#00AA55',
    '#009FD4',
    '#B381B3',
    '#939393',
    '#E3BC00',
    '#D47500',
    '#DC2A2A'
  ];
  constructor(
    private translate: Translate,
    private storageService: StorageService,
    private router: Router,
    private storeSystemService: StoreSystemService
  ) { }

  ngOnInit() {
    this.currentLanguage = this.translate.getCurrentLanguage();
    this.user = this.storageService.getUser();
    this.userID = this.user.employeeID;

    this.processFullName(this.user.employeeName);
    if (this.user.avataURL == null || this.user.avataURL === '') {
      this.showInitials = true;
      this.createInititals(this.user.employeeName);

      const randomIndex = Math.floor(Math.random() * Math.floor(this.colors.length));
      this.circleColor = this.colors[randomIndex];
    }

    this.translate.currentlanguage$.subscribe((lang: string) => {
      if (lang) {
        this.currentLanguage = lang;
      }
    });

    // lang nghe su thay doi avatar o man hinh personal
    this.storeSystemService.avataSubject.subscribe(avataURL => {
      if (avataURL) {
        this.showInitials = false;
        this.user.avataURL = avataURL;
      }
    });
    // lang nghe su thay doi full name o man hinh personal
    this.storeSystemService.fullNameSubject.subscribe(fullName => {
      if (fullName) {
        this.processFullName(fullName);
      }
    });
  }

  ngOnDestroy() {
    this.storeSystemService.clearStore();
  }

  changeLanguage(lang: any) {
    if (lang !== this.currentLanguage) {
      this.translate.changeLanguage(lang);
    }
    return;
  }

  logOut() {
    this.storageService.logOut();
    this.router.navigateByUrl('/login');
  }

  private createInititals(fullName: string): void {
    let initials = "";
    const arrWord = this.user.employeeName.split(' ');
    initials = arrWord[0].charAt(0) + arrWord[arrWord.length - 1].charAt(0);

    this.initials = initials;
  }

  processFullName(fullName: string) {
    if (fullName.length > 14) {
      this.fullName = fullName.substring(0, 14) + '...';
    } else {
      this.fullName = fullName;
    }
  }
}
