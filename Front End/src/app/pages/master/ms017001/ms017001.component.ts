import { Component, OnInit } from '@angular/core';
import { Ms017001Service } from './ms017001.service';
import { Listprops } from '../../../common/page-options';

export interface Permission {
  groupScreenCode: string;
  isCollapsed: boolean;
  listRoleScreen?: any[];
}

export interface RoleScreenItem {
  groupScreenCode?: string;
  roleCode?: string;
  screenCode?: string;
  screenName?: string;
  access?: boolean;
}

@Component({
  selector: 'app-ms017001',
  templateUrl: './ms017001.component.html',
  styleUrls: ['./ms017001.component.scss']
})
export class MS017001Component implements OnInit {
  listRole = [{ roleCode: '' }];
  listListPermission: Permission[] = [];
  screenProps = new Listprops();
  constructor(private ms017001Service: Ms017001Service) { }

  ngOnInit(): void {
    this.ms017001Service.getRoleScreen({}).subscribe(
      data => {
        if (data.content) {
          // create Group Table
          data.content.listGroupScreen.forEach(groupScreen => {
            this.listListPermission.push({ groupScreenCode: groupScreen.groupScreenCode, isCollapsed: true, listRoleScreen: [] });
          });
          // create column name with ROLE_CODE from list ROLE, first columns is NameScreen
          data.content.listRole.forEach(role => {
            this.listRole.push({ roleCode: role.roleCode });
          });
          // add screen to Group Table
          this.createListPermission(data.content.listRole);
        }
      }
    );
  }

  createListPermission(listRole: []) {
    listRole.forEach((role: any) => {
      role.listRoleScreen.forEach(item => {
        this.listListPermission.forEach(rolescreen => {
          if (item.groupScreenCode === rolescreen.groupScreenCode) {
            if (this.checkExitsList(item) === false) {
              const object: any = { screenCode: item.screenCode };
              object[item.roleCode] = item.access;
              rolescreen.listRoleScreen.push(object);
            } else {
              this.addAccessItem(item);
            }
          }
        });
      });
    });
    // sort screenCode
    this.listListPermission.forEach(permission => {
      // tslint:disable-next-line: only-arrow-functions
      permission.listRoleScreen.sort(function(a, b) {
        const nameA = a.screenCode.toUpperCase(); // bỏ qua hoa thường
        const nameB = b.screenCode.toUpperCase(); // bỏ qua hoa thường
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

  checkExitsList(item: any): boolean {
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.listListPermission.length; i++) {
      if (this.listListPermission[i].groupScreenCode === item.groupScreenCode) {
        // tslint:disable-next-line: prefer-for-of
        for (let j = 0; j < this.listListPermission[i].listRoleScreen.length; j++) {
          const element = this.listListPermission[i].listRoleScreen[j];
          if (element.screenCode === item.screenCode) {
            return true;
          }
        }
        return false;
      }
    }
    return false;
  }

  addAccessItem(item: any) {
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.listListPermission.length; i++) {
      if (this.listListPermission[i].groupScreenCode === item.groupScreenCode) {
        // tslint:disable-next-line: prefer-for-of
        for (let j = 0; j < this.listListPermission[i].listRoleScreen.length; j++) {
          const element = this.listListPermission[i].listRoleScreen[j];
          if (element.screenCode === item.screenCode) {
            this.listListPermission[i].listRoleScreen[j][item.roleCode] = item.access;
            return;
          }
        }
      }
    }
  }

  onChange(groupScreenCode, event) {
    const status: boolean = event.target.checked;
    var element = document.getElementById(groupScreenCode);
    if (status) {
      element.style.display = 'block';
    } else {
      element.style.display = 'none';
    }
  }
}
