import {
  Component, EventEmitter,
  Input, OnChanges, OnInit, Output, SimpleChanges
} from '@angular/core';
import { Observable } from 'rxjs';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { Language, LanguageDetail, LevelLanguage, ServerErrors } from '../em001002-entity';

@Component({
  selector: 'app-em001002-language-view',
  templateUrl: './em001002-language-view.component.html',
  styleUrls: ['./em001002-language-view.component.scss']
})
export class Em001002LanguageViewComponent implements OnInit, OnChanges {

  @Input() listLanguage: Language[];
  @Input() listLevelLanguage: LevelLanguage[];
  @Input() listLanguageDetail: LanguageDetail[];
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isProfile: boolean;
  @Output() submitForm: EventEmitter<any> = new EventEmitter();
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() isEditable: EventEmitter<boolean> = new EventEmitter();
  @Output() addNewLanguage: EventEmitter<boolean> = new EventEmitter();

  languagesMap = new Map();

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes) {
      this.initData();
    }
  }

  ngOnInit(): void {
  }

  initData() {
    if (this.listLanguageDetail) {
      this.listLanguageDetail.forEach(element => {
        if (this.languagesMap.has(element.languageCategoryName)) {
          const listLanguageNameOld: LanguageDetail[] = this.languagesMap.get(element.languageCategoryName);
          listLanguageNameOld.push(element);
          // sort desc by levelSkillValue
          // listSkillNameOld.sort((a, b) => {
          //   return +b.levelSkillValue - +a.levelSkillValue;
          // });
          this.languagesMap.set(element.languageCategoryName, listLanguageNameOld);
        } else {
          const listLanguageNameNew: LanguageDetail[] = [];
          listLanguageNameNew.push(element);
          this.languagesMap.set(element.languageCategoryName, listLanguageNameNew);
        }
      });
    }
  }

  onEditLanguage() {
    this.isEditable.emit(true);
  }

  openPopup() {
    this.back.emit('back');
  }

  addLanguage() {
    this.addNewLanguage.emit(true);
  }
}
