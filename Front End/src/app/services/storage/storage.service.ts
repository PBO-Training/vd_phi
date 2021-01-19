import { FormBuilder, FormGroup } from '@angular/forms';
import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const AUTH_ROLE = 'auth-role';
const AUTH_MENU = 'auth-menu';
const AUTH_USER = 'auth-user';
const PAGE_SIZE = 'page-size';
const COMPANY_CODE = 'company-code';
const ACTIVE_TAB_EMPLOYEE = 'active-tab-employee';
const ACTIVE_TAB_PROJECT = 'active-tab-project';
const PROJECT_ID = 'project-id';
const BACK_FLAG = 'back-flag';
const BACK_FLAG_EXPAND = 'back-flag-expand';
const SEARCH_DATA = 'search-data';

@Injectable({
  providedIn: 'platform'
})
export class StorageService {
  constructor() {

  }
  public savePageSizeLocal(pageSize: number) {
    window.localStorage.setItem(PAGE_SIZE, pageSize.toString());
  }
  public getPageSize() {
    return window.localStorage.getItem(PAGE_SIZE);
  }
  public saveToken(accessToken: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, accessToken);
  }

  public getToken(): string {
    return window.localStorage.getItem(TOKEN_KEY);
  }

  public logOut() {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.removeItem(AUTH_ROLE);
    window.localStorage.removeItem(AUTH_MENU);
    window.localStorage.removeItem(AUTH_USER);
    window.localStorage.removeItem(ACTIVE_TAB_EMPLOYEE);
    window.localStorage.removeItem(BACK_FLAG);
    // window.localStorage.removeItem(COMPANY_CODE);
    window.localStorage.removeItem(ACTIVE_TAB_PROJECT);
    window.localStorage.removeItem(SEARCH_DATA);
  }

  public saveRole(role: object) {
    window.localStorage.removeItem(AUTH_ROLE);
    window.localStorage.setItem(AUTH_ROLE, JSON.stringify(role));
  }

  public getRole(): object {
    return JSON.parse(window.localStorage.getItem(AUTH_ROLE));
  }

  public saveMenu(menu: object) {
    window.localStorage.removeItem(AUTH_MENU);
    window.localStorage.setItem(AUTH_MENU, JSON.stringify(menu));
  }

  public getMenu(): object {
    return JSON.parse(window.localStorage.getItem(AUTH_MENU));
  }

  public saveUser(user: object) {
    window.localStorage.removeItem(AUTH_USER);
    window.localStorage.setItem(AUTH_USER, JSON.stringify(user));
  }

  public getUser(): object {
    return JSON.parse(window.localStorage.getItem(AUTH_USER));
  }

  public saveCompanyCode(companyCode: string) {
    window.localStorage.setItem(COMPANY_CODE, JSON.stringify(companyCode));
  }

  public getCompanyCode(): object {
    return JSON.parse(window.localStorage.getItem(COMPANY_CODE));
  }

  public saveActiveTabEmployee(tabIndex: number) {
    window.localStorage.removeItem(ACTIVE_TAB_EMPLOYEE);
    window.localStorage.setItem(ACTIVE_TAB_EMPLOYEE, JSON.stringify(tabIndex));
  }

  public getActiveTabEmployee(): number {
    return JSON.parse(window.localStorage.getItem(ACTIVE_TAB_EMPLOYEE));
  }

  public saveActiveTabProject(tabIndex: number) {
    window.localStorage.removeItem(ACTIVE_TAB_PROJECT);
    window.localStorage.setItem(ACTIVE_TAB_PROJECT, JSON.stringify(tabIndex));
  }

  public getActiveTabProject(): number {
    return JSON.parse(window.localStorage.getItem(ACTIVE_TAB_PROJECT));
  }

  public removeActiveTabProject(): void {
    window.localStorage.removeItem(ACTIVE_TAB_PROJECT);
  }
  public saveProjectID(tabIndex: number) {
    window.localStorage.removeItem(PROJECT_ID);
    window.localStorage.setItem(PROJECT_ID, JSON.stringify(tabIndex));
  }

  public getProjectID(): number {
    return JSON.parse(window.localStorage.getItem(PROJECT_ID));
  }

  public removeProjectID(): void {
    window.localStorage.removeItem(PROJECT_ID);
  }

  public saveSearchData(searchData: any, page: any) {
    const data = {
      data: searchData,
      currentPage: page
    };
    window.localStorage.setItem(SEARCH_DATA, JSON.stringify(data));
  }

  public getSearchData(): any {
    const data = JSON.parse(window.localStorage.getItem(SEARCH_DATA));
    return data;
  }

  public removeSearchData(): void {
    window.localStorage.removeItem(SEARCH_DATA);
  }

  public saveBackFlag(backFlag: boolean) {
    window.localStorage.removeItem(BACK_FLAG);
    window.localStorage.setItem(BACK_FLAG, JSON.stringify(backFlag));
  }

  public getBackFlag(): boolean {
    return JSON.parse(window.localStorage.getItem(BACK_FLAG));
  }

  public removeBackFlag(): void {
    window.localStorage.removeItem(BACK_FLAG);
  }

  public saveSearchDataWithKey(searchData: any, keyScreen: string) {
    window.localStorage.removeItem(keyScreen);
    window.localStorage.setItem(keyScreen, JSON.stringify(searchData));
  }

  public removeSearchDataWithKey(keyScreen: string): void {
    window.localStorage.removeItem(keyScreen);
  }

  public saveBackFlagExpand(backFlag: boolean) {
    window.localStorage.removeItem(BACK_FLAG_EXPAND);
    window.localStorage.setItem(BACK_FLAG_EXPAND, JSON.stringify(backFlag));
  }

  public getBackFlagExpand(): boolean {
    return JSON.parse(window.localStorage.getItem(BACK_FLAG_EXPAND));
  }

  public getSearchDataWithKey(keyScreen: string): any {
    return JSON.parse(window.localStorage.getItem(keyScreen));
  }
}
