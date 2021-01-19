import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NgbDatepickerModule, NgbNavModule, NgbProgressbar, NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { SharedModule } from '../../../theme/shared/shared.module';
import { Em001002HistoryComponent } from './em001002-history/em001002-history.component';
import { Em001002LanguageComponent } from './em001002-language/em001002-language.component';
import { Em001002PersonalComponent } from './em001002-personal/em001002-personal.component';
import { Em001002SkillComponent } from './em001002-skill/em001002-skill.component';
import { StoreService } from './em001002-store-service';
import { Em001002Component } from './em001002.component';
import { Em001002ContractComponent } from './em001002-contract/em001002-contract.component';
import { Em001002SkillLanguageComponent } from './em001002-skill-language/em001002-skill-language.component';
import { Em001002PersonalViewComponent } from './em001002-personal-view/em001002-personal-view.component';
import { Em001002ModalAddSkillComponent } from './em001002-skill-language/em001002-modal-add-skill/em001002-modal-add-skill.component';
import { Em001002HistoryViewComponent } from './em001002-history-view/em001002-history-view.component';
import { Em001002ContractViewComponent } from './em001002-contract-view/em001002-contract-view.component';
import { Em001002SkillViewComponent } from './em001002-skill-view/em001002-skill-view.component';
import { Em001002LanguageViewComponent } from './em001002-language-view/em001002-language-view.component';


const routes: Routes = [{
    path: '',
    component: Em001002Component
}];

@NgModule({
    imports: [
        SharedModule,
        NgbNavModule,
        NgbDatepickerModule,
        RouterModule.forChild(routes),
        NgSelectModule,
        NgbProgressbarModule
    ],
    exports: [],
    declarations: [
        Em001002Component,
        Em001002PersonalComponent,
        Em001002SkillComponent,
        Em001002LanguageComponent,
        Em001002HistoryComponent,
        Em001002ContractComponent,
        Em001002SkillLanguageComponent,
        Em001002PersonalViewComponent,
        Em001002ModalAddSkillComponent,
        Em001002PersonalViewComponent,
        Em001002HistoryViewComponent,
        Em001002ContractViewComponent,
        Em001002SkillViewComponent,
        Em001002LanguageViewComponent
    ],
    providers: [
        StoreService
    ],
    entryComponents: [
    ],
})
export class Em001002Module { }
