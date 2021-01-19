import {
  Component,
  EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges
} from '@angular/core';
import { Observable } from 'rxjs';
import { ScreenAction } from '../../../../common/screen-action/screen-action';
import { LevelSkill, ServerErrors, Skill, SkillDetail, SkillType } from '../em001002-entity';

@Component({
  selector: 'app-em001002-skill-view',
  templateUrl: './em001002-skill-view.component.html',
  styleUrls: ['./em001002-skill-view.component.scss']
})
export class Em001002SkillViewComponent implements OnInit, OnChanges {

  @Input() listSkill: Skill[];
  @Input() listLevelSkill: LevelSkill[];
  @Input() listSkillType: SkillType[];
  @Input() listSkillDetail: SkillDetail[];
  @Input() serverErrors$: Observable<ServerErrors>;
  @Input() authButton: ScreenAction;
  @Input() isProfile: boolean;
  @Output() back: EventEmitter<any> = new EventEmitter();
  @Output() isEditable: EventEmitter<boolean> = new EventEmitter();
  @Output() addNewSkill: EventEmitter<boolean> = new EventEmitter();

  skillsMap = new Map();

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes) {
      this.initData();
    }
  }

  ngOnInit(): void {
  }

  initData() {
    if (this.listSkillDetail) {
      this.listSkillDetail.forEach(element => {
        if (this.skillsMap.has(element.skillTypeName)) {
          const listSkillNameOld: SkillDetail[] = this.skillsMap.get(element.skillTypeName);
          listSkillNameOld.push(element);
          // sort desc by levelSkillValue
          listSkillNameOld.sort((a, b) => {
            return +b.levelSkillValue - +a.levelSkillValue;
          });
          this.skillsMap.set(element.skillTypeName, listSkillNameOld);
        } else {
          const listSkillNameNew: SkillDetail[] = [];
          listSkillNameNew.push(element);
          this.skillsMap.set(element.skillTypeName, listSkillNameNew);
        }
      });
    }
  }

  onEditSkill() {
    this.isEditable.emit(true);
  }

  openPopup() {
    this.back.emit('back');
  }

  addSkill() {
    this.addNewSkill.emit(true);
  }

}
