import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Ms000000Service } from './ms000000.service';
import { StorageService } from '../../../services/storage/storage.service';
import { Screen, User } from './ms000000-entity';

export interface NavigationItem {
  id: string;
  title: string;
  type: 'item' | 'collapse' | 'group';
  translate?: string;
  icon?: string;
  url?: string;
  index?: number;
  children?: NavigationItem[];
}

@Component({
  selector: 'app-ms000000',
  templateUrl: './ms000000.component.html',
  styleUrls: ['./ms000000.component.scss']
})
export class Ms000000Component implements OnInit {
  @ViewChild('userName') searchElement: ElementRef;

  formData: FormGroup;
  submitted = false;
  loading = false;
  returnUrl: string;
  error = '';
  isBadCredential = false;
  user: User;
  navigationMenu: NavigationItem[] = [
    {
      id: 'Menu',
      title: '',
      type: 'group',
      icon: 'feather icon-monitor',
      children: []
    }
  ];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private loginService: Ms000000Service,
    private storageService: StorageService
  ) {
    // redirect to home if already logged in
    if (this.storageService.getToken()) {
      this.router.navigateByUrl('');
    }
  }

  ngOnInit(): void {
    this.formData = this.fb.group(
      {
        username: ['', [Validators.required]],
        password: ['', [Validators.required]],
        companyCode: [this.storageService.getCompanyCode(), [Validators.required]],
      }
    );

    if (this.storageService.getCompanyCode() !== null) {
      setTimeout(() => { // this will make the execution after the above boolean has changed
        this.searchElement.nativeElement.focus();
      }, 0);
    }

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
  }

  get f() { return this.formData.controls; }

  submitForm(dataLogin) {
    this.submitted = true;
    // stop here if form is invalid
    if (this.formData.invalid) {
      return;
    }
    this.loading = true;
    this.loginService.login(dataLogin).subscribe(
      (res) => {
        if (res.content) {
          this.storageService.saveToken(res.content.accessToken);
          this.storageService.saveRole(res.content.role);
          this.storageService.saveCompanyCode(res.content.companyCode);
          this.user = {
            username: res.content.username,
            employeeID: res.content.employeeID,
            employeeName: res.content.employeeName,
            avataURL: res.content.avataURL,
            companyCode: res.content.companyCode,
            companyName: res.content.companyName
          };
          this.storageService.saveUser(this.user);
          this.createMenu(res.content.role.listScreen);
          this.storageService.saveMenu(this.navigationMenu);
          this.router.navigate([this.returnUrl]);
        }
      },
      (err) => {
        if (err.error) {
          this.isBadCredential = true;
        }
        this.error = err;
        this.loading = false;
      }
    );
  }

  createMenu(listScreen: Screen[]) {
    // add Group
    listScreen.forEach(screen => {
      if (screen.groupScreenName != null) {
        if (this.checkGroupExist(screen.groupScreenName) === false && screen.access === true && this.screenIsParent(screen.screenUrl)) {
          this.navigationMenu[0].children.push(
            {
              id: screen.groupScreenName,
              title: screen.groupScreenName,
              type: 'collapse',
              icon: 'feather icon-' + screen.groupIcon,
              index: screen.groupIndex,
              children: []
            }
          );
        }
      }
    });
    // sort Menu Index
    // tslint:disable-next-line: only-arrow-functions
    this.navigationMenu[0].children.sort(function(a, b) {
      return a.index - b.index;
    });
    // add item group
    listScreen.forEach(screen => {
      this.navigationMenu[0].children.forEach((group) => {
        if (group.id === screen.groupScreenName && screen.access === true) {
          // screen is group
          if (screen.screenLevel === 1) {
            group.title = screen.screenName;
            group.type = 'item';
            group.url = '/' + screen.screenUrl;
          } else if (this.screenIsParent(screen.screenUrl)) {// screen of group
            group.children.push(
              {
                id: screen.groupScreenName,
                title: screen.screenName,
                type: 'item',
                url: '/' + screen.screenUrl,
              }
            );
          }
        }
      });
    });
    // sort item group
    this.navigationMenu[0].children.forEach((group) => {
      // tslint:disable-next-line: only-arrow-functions
      group.children.sort(function(a, b) {
        const nameA = a.title.toUpperCase(); // bỏ qua hoa thường
        const nameB = b.title.toUpperCase(); // bỏ qua hoa thường
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
        // name trùng nhau
        return 0;
      });
    });
  }

  checkGroupExist(groupScreenName: string): boolean {
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.navigationMenu[0].children.length; i++) {
      if (this.navigationMenu[0].children[i].id === groupScreenName) {
        return true;
      }
    }
    return false;
  }

  onKey(event) {
    if (event.key !== 'Enter' && this.isBadCredential) {
      this.isBadCredential = false;
    }
  }

  screenIsParent(screenUrl: string): boolean {
    if (screenUrl.indexOf('/') > 0) {
      return false;
    }
    return true;
  }
}
