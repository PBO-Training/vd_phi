import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin/admin.component';
import { AuthComponent } from './auth/auth.component';
import { NavigationComponent } from './admin/navigation/navigation.component';
import { NavContentComponent } from './admin/navigation/nav-content/nav-content.component';
import { NavGroupComponent } from './admin/navigation/nav-content/nav-group/nav-group.component';
import { NavCollapseComponent } from './admin/navigation/nav-content/nav-collapse/nav-collapse.component';
import { NavItemComponent } from './admin/navigation/nav-content/nav-item/nav-item.component';
import { NavRightComponent } from './admin/nav-bar/nav-right/nav-right.component';
import { ConfigurationComponent } from './admin/configuration/configuration.component';
import { ToggleFullScreenDirective } from '../shared/full-screen/toggle-full-screen';
import { NavigationItem } from './admin/navigation/navigation';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { NgbDropdownModule, NgbTooltipModule, NgbButtonsModule, NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { NavBarComponent } from './admin/nav-bar/nav-bar.component';



@NgModule({
    imports: [CommonModule,
        NgbDropdownModule,
        NgbTooltipModule,
        NgbButtonsModule,
        NgbTabsetModule,
        SharedModule,
        RouterModule
    ],
    exports: [],
    declarations: [
        AdminComponent,
        AuthComponent,
        NavigationComponent,
        NavContentComponent,
        NavGroupComponent,
        NavCollapseComponent,
        NavItemComponent,
        NavBarComponent,
        NavRightComponent,
        ConfigurationComponent,
        ToggleFullScreenDirective
    ],
    providers: [NavigationItem,
    ],
})
export class ThemeLayoutModule { }
