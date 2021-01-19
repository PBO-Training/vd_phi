import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Listprops } from '../../../../common/page-options';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { CommonFunctionService } from '../../../../services/common-function/common-function.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { ModalConfirmComponent } from '../../../../theme/shared/components/modal-confirm/modal-confirm.component';
import { Pm001002Service } from '../pm001002.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import * as fs from 'file-saver';
import { DocumentResponse } from '../pm001002-entity';

@Component({
    selector: 'app-tab-document',
    templateUrl: './pm001002-tab-document.component.html',
    styleUrls: ['./pm001002-tab-document.component.scss']
})
export class Pm001002TabDocumentComponent implements OnInit, OnChanges {
    @Input() listDocument: Array<DocumentResponse>;
    @Output() ReGetListDocument = new EventEmitter<boolean>();
    @Output() navigateToCreateDocument = new EventEmitter<boolean>();
    authButton = new ScreenAction();
    screenProps = new Listprops();
    private unsubscribe$ = new Subject();
    indeterminate = false;
    checked = false;
    fileDownload: any;

    constructor(
        private translateService: TranslateService,
        private modalService: NgbModal,
        private projectService: Pm001002Service,
        public toastService: ToastService,
        private commonFunctionService: CommonFunctionService
    ) {
        this.authButton = this.commonFunctionService.initAuthAction(this.authButton);
    }

    ngOnChanges(changes: SimpleChanges): void {

    }

    ngOnInit(): void {
    }

    // Event click button delete
    deleteDocument() {
        if (this.screenProps.setOfCheckedId.size > 0) {
            this.screenProps.setOfCheckedId.forEach(x => {
                this.screenProps.ids.push(x);
            });
            this.openPopup(this.screenProps.ids);
        } else {
            this.translateService.get('notification-message.unchecked').subscribe((text: string) => {
                this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
            });
        }
    }

    // Process to show popup
    openPopup(ids: number[]) {
        // NgbModal bootstrap using abtract ViewContainerRef of angular create dynamic components, https://angular.io/api/core/ViewContainerRef;
        const modalRef = this.modalService.open(ModalConfirmComponent);
        // COMUNICATION WITH DYNAMIC COMPONENTS USING @Input() or @Ouput() with componentInstance
        this.translateService.get('confirm-message.delete').subscribe(
            (text: string) => {
                modalRef.componentInstance.title = text;
                modalRef.result.then(result => {
                    if (result === 'delete') {
                        this.projectService.pm001002DeleteDocument(ids).pipe(takeUntil(this.unsubscribe$)).subscribe(
                            (res: any) => {
                                if (res.error === null) {
                                    this.screenProps.ids = [];
                                    this.screenProps.setOfCheckedId.clear();
                                    this.toastService.show('notification-message.delete-success', { classname: 'bg-success text-light', delay: 3000 });
                                    this.ReGetListDocument.emit(true);
                                } else {
                                    // show toast if delete fail
                                    this.translateService.get('notification-message.delete-fail').subscribe((message: string) => {
                                        this.toastService.show(message, { classname: 'bg-danger text-light', delay: 3000 });
                                    });
                                }
                            }
                        );
                    }
                }, reason => {
                });
            }
        );
    }

    refreshCheckedStatus(): void {
        this.checked = this.listDocument.every(item => this.screenProps.setOfCheckedId.has(item.documentID));
        this.indeterminate = this.listDocument.some(item => this.screenProps.setOfCheckedId.has(item.documentID)) && !this.checked;
    }

    resetCheckedSet(): void {
        this.checked = false;
        this.screenProps.setOfCheckedId.clear();
        this.refreshCheckedStatus();
    }

    // Process to check or uncheck
    updateCheckedSet(id: number, checked: boolean): void {
        if (checked) {
            this.screenProps.setOfCheckedId.add(id);
        } else {
            this.screenProps.setOfCheckedId.delete(id);
        }
    }

    // Event click on checkbox
    onCheckedItem(id: number, event): void {
        this.updateCheckedSet(id, event.target.checked);
    }

    // Event click on checkbox check all
    onCheckedAll(event) {
        this.listDocument.forEach(item => this.updateCheckedSet(item.documentID, event.target.checked));
        this.refreshCheckedStatus();
    }

    // Event click on documentName
    downloadDocFile(documentID: number, documentName: string) {
        this.projectService.getDocumentFile(documentID).pipe(takeUntil(this.unsubscribe$)).subscribe((data) => {
            if (data) {
                fs.saveAs(data, documentName);
            }
        },
            err => {
                if (err) {
                    this.toastService.show('File Not Found', { classname: 'bg-warning text-light', delay: 3000 });
                }
            });
    }

    // Event click button create
    navigateDetailDocument = (value: boolean) => {
        this.navigateToCreateDocument.emit(value);
    }
}
