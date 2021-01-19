import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import * as url from '../../../common/constant/api-url';

@Injectable({ providedIn: 'root' })
export class Pm001002Service {

    constructor(
        private http: HttpClient
    ) { }

    // API Project info
    /*
    * Use to call api init project
    */
    pm001002Init(): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.ProjectCreateInit}`, {});
    }

    /*
    * Use to call api create project
    */
    pm001002Create(project: {}): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.ProjectCreate}`, project);
    }

    /*
    * Use to call api update project
    */
    pm001002Update(project: {}): Observable<any> {
        return this.http.put<any>(`${environment.apiUrl}${url.ProjectApiUrl.ProjectUpdate}`, project);
    }

    /*
    * Use to call api get detail project
    */
    pm001002GetDetail(id: number): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.ProjectGetDetail}`, { projectID: id });
    }

    // API Issue
    /*
    * Use to call api search issue
    */
    pm001002SearchIssue(object: {}): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.IssueSearch}`, object);
    }

    /*
    * Use to call api delete issue
    */
    pm001002DeleteIssue(ids: any) {
        const httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete: ids },
        };
        return this.http.delete<any>(`${environment.apiUrl}${url.ProjectApiUrl.IssueDelete}`, httpOptions);
    }

    /*
    * Use to call api init data to issue
    */
    pm001002InitIssue(): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.IssueSearchInit}`, {});
    }

    /*
    * Use to call api get list employee in a project(like api search)
    */
    pm001002GetListMember(object: {}): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.MemberGetList}`, object);
    }

    /*
    * Use to call api init data of tab member
    */
    pm001002InitMember(): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.MemberInit}`, {});
    }

    /*
    * Use to call api update info member of tab member
    */
    pm001002UpdateInfoMember(object: {}): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.MemberUpdateInfo}`, object);
    }

    /*
    * Use to call api update info member of tab member
    */
    pm001002AddMembers(object: {}): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.MemberAddToProject}`, object);
    }

    /*
    * Use to call api search employee of employee-service
    */
    getListEmployee(formListMember: any): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.EmployeeApiUrl.EmployeeSearch}`, formListMember);

    }

    /*
    * Use to call api init user of master-service
    */
    initDropDownList(): Observable<any> {
        return this.http.post<any[]>(`${environment.apiUrl}${url.UserApiUrl.UserInit}`, {
            headers: new HttpHeaders()
                .set('Content-Type', 'application/json')
        });
      }

    removeMember(member: {}): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.MemberRemoveFromProject}`, member);
    }
    /*
    * Use to call api get detail tracker
    */
    pm001002GetDetailTracker(trackerID: number): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.GetDetailTracker}`, { trackerID });
    }

    /*
    * Use to call api init data to issue detail
    */
    pm001002InitIssueDetail(): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.IssueDetailInit}`, {});
    }

    /*
    * Use to call api init data to member detail
    */
   pm001002InitMemberDetail(): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.MemberDetailInit}`, {});
    }

    /*
    * Use to call api get data to member detail
    */
    pm001002GetDetailMember(data: any): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.MemberGetDetail}`, data);
    }

    /*
    * Use to call api create issue
    */
    pm001002CreateIssue(issue: {}): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.IssueDetailCreate}`, issue);
    }

    /*
    * Use to call api update issue
    */
    pm001002UpdateIssue(issue: {}): Observable<any> {
        return this.http.put<any>(`${environment.apiUrl}${url.ProjectApiUrl.IssueDetailUpdate}`, issue);
    }

    /*
    * Use to call api get detail issue
    */
    pm001002GetDetailIssue(issueID): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.IssueGetDetail}`, { issueID });
    }

    // API Document
    /*
    * Use to call api get document
    */
    pm001002GetDocument(projectID): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.GetDocument}`, { projectID });
    }

    /*
    * Use to call api delete document
    */
    pm001002DeleteDocument(listDelete): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json' }), body: { listDelete },
        };
        return this.http.delete<any>(`${environment.apiUrl}${url.ProjectApiUrl.deleteDocument}`, httpOptions);
    }

    /*
    * Use to call api get document file
    */
    getDocumentFile(documentID: number): Observable<any> {
        return this.http.get(`${environment.apiUrl}${url.ProjectApiUrl.GetDocumentFile}/${documentID}`, {
            responseType: 'blob'
        });
    }

    /*
    * Use to call api create document
    */
    pm002003CreateDocument(data: FormData): Observable<any> {
        return this.http.post<any>(`${environment.apiUrl}${url.ProjectApiUrl.CreateDocument}`, data);
    }
}
