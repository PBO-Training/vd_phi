import {
  Component, ElementRef,
  EventEmitter, Input, OnChanges, OnInit, Output, QueryList, SimpleChanges, ViewChildren
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { ExportType } from '../../../../common/constant/type';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { ExportExcelService } from '../../../../services/export-excel/export-excel.service';
import { ToastService } from '../../../../theme/shared/components/toast-container/toast-service';
import { EM001001SearchService } from '../../em001001/em001001.service';
import { EmployeeToExportHistory } from '../../em001001/employee-request';
// tslint:disable-next-line:max-line-length
import { Database, EvaluateEmployeeProject, HistoryWork, InitScopeWork, OperationSystem, PositionProject, ServerErrors, Skill } from '../em001002-entity';

@Component({
  selector: 'app-em001002-history-view',
  templateUrl: './em001002-history-view.component.html',
  styleUrls: ['./em001002-history-view.component.scss']
})
export class Em001002HistoryViewComponent implements OnInit, OnChanges {
  @ViewChildren('appHistory', { read: ElementRef }) appHistory: QueryList<ElementRef>;

  @Input() listHistory: HistoryWork[];
  @Input() companyName: string;
  @Input() listOperationSystem: OperationSystem[];
  @Input() listPositionProject: PositionProject[];
  @Input() listDatabase: Database[];
  @Input() listScopeWork: InitScopeWork[];
  @Input() listSkill: Skill[];
  @Input() listEvaluateEmployeeProject: EvaluateEmployeeProject[];
  @Input() authButton: ScreenAction;
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() isEditable: EventEmitter<boolean> = new EventEmitter();
  @Output() addNewHistory: EventEmitter<boolean> = new EventEmitter();

  exportForm: FormGroup;
  listEmpExportHistory: EmployeeToExportHistory[] = [];
  historysMap = new Map();

  constructor(
    private router: ActivatedRoute,
    public toastService: ToastService,
    private translateService: TranslateService,
    private formBuilder: FormBuilder,
    private exportExcelService: ExportExcelService,
    private employeeService: EM001001SearchService,
  ) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes) {
      this.initData();
    }
  }

  ngOnInit(): void {
    this.exportForm = this.formBuilder.group({
      exportType: [null],
      listID: [[]]
    });
  }

  initData() {
    this.historysMap.set('inside', []);
    this.historysMap.set('outside', []);
    // Display record
    if (this.listHistory) {
      this.listHistory.forEach((history, index) => {
        let listScopeName = '';
        let roleName = '';
        let osName = '';
        let listSkillProjectName = '';
        let dbName = '';
        let evalName = '';
        history.listScopeWork.forEach((scope, i) => {
          if (i !== 0) {
            listScopeName += ', ';
          }
          listScopeName += scope.scopeWorkName;
        });
        this.listPositionProject.forEach(role => {
          if (history.positionProjectID === role.positionProjectID) {
            roleName = role.positionProjectName;
          }
        });
        this.listOperationSystem.forEach(os => {
          if (history.operationSystemID === os.operationSystemID) {
            osName = os.operationSystemName;
          }
        });
        this.listDatabase.forEach(db => {
          if (history.databaseID === db.databaseHistoryID) {
            dbName = db.databaseHistoryName;
          }
        });
        history.listSkill.forEach((skill, i) => {
          if (i !== 0) {
            listSkillProjectName += ', ';
          }
          listSkillProjectName += skill.skillName;
        });
        this.listEvaluateEmployeeProject.forEach(evaluate => {
          if (history.evaluateEmployeeProjectID === evaluate.evaluateEmployeeProjectID) {
            evalName = evaluate.evaluateEmployeeProjectName;
          }
        });
        history = {
          ...history,
          listScopeWorkName: listScopeName,
          evaluateEmployeeProjectName: evalName,
          listSkillName: listSkillProjectName,
          operationSystemName: osName,
          databaseName: dbName,
          positionProjectName: roleName
        };
        if (history.projectID === null) {
          const listHistoryWorkNew: HistoryWork[] = this.historysMap.get('outside');
          listHistoryWorkNew.push(history);
        } else {
          const listHistoryWorkNew: HistoryWork[] = this.historysMap.get('inside');
          listHistoryWorkNew.push(history);
        }
      });
    }
  }

  onEditHistory() {
    this.isEditable.emit(true);
  }

  openPopup() {
    this.back.emit('back');
  }

  addHistory() {
    this.addNewHistory.emit(true);
  }

  exportHistory() {
    const employeeID = this.router.snapshot.params.id;
    if (employeeID) {
      const listID: number[] = [employeeID];
      this.exportForm.value.exportType = ExportType.HISTORY;
      this.exportForm.value.listID = listID;
      this.employeeService.em001001Export(this.exportForm.value).subscribe(data => {
        this.listEmpExportHistory = data.content;
        this.exportExcelService.createEmployeeHistoryExcelFiles('技術経歴書', this.listEmpExportHistory);
      });
    } else {
      this.translateService.get('notification-message.export-unchecked').subscribe((text: string) => {
        this.toastService.show(text, { classname: 'bg-warning text-light', delay: 3000 });
      });
      return;
    }
  }
}
