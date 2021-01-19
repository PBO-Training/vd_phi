import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Ms000000Component } from './pages/master/ms000000/ms000000.component';
import { AuthGuard } from './services/guard/auth.guard';
import { AdminComponent } from './theme/layout/admin/admin.component';

const routes: Routes = [
  {
    path: '', pathMatch: 'full', redirectTo: 'dashboard'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/master/ms000000/ms000000.module').then(module => module.Ms000000Module),
    component: Ms000000Component,
    canActivate: [AuthGuard]
  },
  {
    path: '',
    component: AdminComponent,
    children: [
      // Dashboard
      {
        path: 'dashboard',
        loadChildren: () => import('./pages/master/ms999001/ms999001.module')
          .then(module => module.Ms999001Module),
        canActivate: [AuthGuard]
      },

      // Error
      {
        path: '403',
        loadChildren: () => import('./pages/error/forbidden403/forbidden403.module')
          .then(module => module.Forbidden403Module)
      },
      {
        path: '404',
        loadChildren: () => import('./pages/error/notfound404/notfound404.module')
          .then(module => module.Notfound404Module)
      },

      // Profile
      {
        path: 'profile',
        loadChildren: () => import('./pages/employee/em001002/em001002.module')
          .then(module => module.Em001002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'profile/detail/:id',
        loadChildren: () => import('./pages/employee/em001002/em001002.module')
          .then(module => module.Em001002Module),
        // canActivate: [AuthGuard]
      },
      {
        path: 'update-password',
        loadChildren: () => import('./pages/employee/em002005/em002005.module').then(m => m.EM002005Module),
        // canActivate: [AuthGuard]
      },

      // Employee
      {
        path: 'employee',
        loadChildren: () => import('./pages/employee/em001001/em001001.module').then(module => module.EM001001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee/create',
        loadChildren: () => import('./pages/employee/em001002/em001002.module')
          .then(module => module.Em001002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee/detail/:id',
        loadChildren: () => import('./pages/employee/em001002/em001002.module')
          .then(module => module.Em001002Module),
        canActivate: [AuthGuard]
      },

      // Project
      {
        path: 'project',
        loadChildren: () => import('./pages/project/pm001001/pm001001.module').then(m => m.Pm001001SearchModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'project/create',
        loadChildren: () => import('./pages/project/pm001002/pm001002.module').then(m => m.Pm001002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'project/detail/:id',
        loadChildren: () => import('./pages/project/pm001002/pm001002.module').then(m => m.Pm001002Module),
        canActivate: [AuthGuard]
      },

      // Vacation
      {
        path: 'vacation-send-list/create',
        loadChildren: () => import('./pages/vacation/vm001002/vm001002.module').then(m => m.VM001002Module),
        canActivate: [AuthGuard],
      },
      {
        path: 'vacation-send-list/detail/:id',
        loadChildren: () => import('./pages/vacation/vm001002/vm001002.module').then(m => m.VM001002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-receive-list/detail/:id',
        loadChildren: () => import('./pages/vacation/vm002002/vm002002.module').then(m => m.Vm002002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-history-list/detail/:id',
        loadChildren: () => import('./pages/vacation/vm003002/Vm003002.module').then(m => m.Vm003002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-summary-list/detail/:id/:year',
        loadChildren: () => import('./pages/vacation/vm003001/vm003001.module').then(m => m.VM003001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-send-list',
        loadChildren: () => import('./pages/vacation/vm001001/vm001001.module').then(m => m.VM001001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-receive-list',
        loadChildren: () => import('./pages/vacation/vm002001/vm002001.module').then(m => m.VM002001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-history-list',
        loadChildren: () => import('./pages/vacation/vm003001/vm003001.module').then(m => m.VM003001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-summary-list',
        loadChildren: () => import('./pages/vacation/vm004001/vm004001.module').then(m => m.VM004001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'timekeeping',
        loadChildren: () => import('./pages/vacation/vm005001/vm005001.module').then(m => m.VM005001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'timekeeping/import/:id',
        loadChildren: () => import('./pages/vacation/vm005002/vm005002.module').then(m => m.VM005002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'timekeeping/detail/:id',
        loadChildren: () => import('./pages/vacation/vm005003/vm005003.module').then(m => m.VM005003Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'timekeeping/report/:id',
        loadChildren: () => import('./pages/vacation/vm005004/vm005004.module').then(m => m.VM005004Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'tracking-timekeeping',
        loadChildren: () => import('./pages/vacation/vm005005/vm005005.module').then(m => m.VM005005Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'shift-work',
        loadChildren: () => import('./pages/vacation/vm006001/vm006001.module').then(m => m.VM006001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'shift-work/create',
        loadChildren: () => import('./pages/vacation/vm006002/vm006002.module').then(m => m.VM006002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'shift-work/detail/:id',
        loadChildren: () => import('./pages/vacation/vm006002/vm006002.module').then(m => m.VM006002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'shift-work-receive',
        loadChildren: () => import('./pages/vacation/vm007001/vm007001.module').then(m => m.VM007001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'shift-work-receive/detail/:id',
        loadChildren: () => import('./pages/vacation/vm007002/vm007002.module').then(m => m.VM007002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'shift-work-history',
        loadChildren: () => import('./pages/vacation/vm008001/vm008001.module').then(m => m.VM008001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'shift-work-history/detail/:id',
        loadChildren: () => import('./pages/vacation/vm008002/vm008002.module').then(m => m.VM008002Module),
        canActivate: [AuthGuard]
      },
      // Master
      {
        path: 'department',
        loadChildren: () => import('./pages/master/ms002001/ms002001.module').then(m => m.MS002001Module),
        data: { animation: 'DepartmentPage' },
        canActivate: [AuthGuard]
      },
      {
        path: 'department/detail/:id',
        loadChildren: () => import('./pages/master/ms002002/ms002002.module').then(m => m.MS002002Module),
        data: { animation: 'DeparmentDetailPage' },
        canActivate: [AuthGuard]
      },
      {
        path: 'department/create',
        loadChildren: () => import('./pages/master/ms002002/ms002002.module').then(m => m.MS002002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'language',
        loadChildren: () => import('./pages/master/ms005001/ms005001.module').then(m => m.MS005001SearchModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'language/detail/:id',
        loadChildren: () => import('./pages/master/ms005002/ms005002.module').then(m => m.MS005002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'language/create',
        loadChildren: () => import('./pages/master/ms005002/ms005002.module').then(m => m.MS005002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'levelLanguage',
        loadChildren: () => import('./pages/master/ms006001/ms006001.module').then(m => m.MS006001SearchModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'levelLanguage/detail/:id',
        loadChildren: () => import('./pages/master/ms006002/ms006002.module').then(m => m.MS006002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'levelLanguage/create',
        loadChildren: () => import('./pages/master/ms006002/ms006002.module').then(m => m.MS006002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'skill',
        loadChildren: () => import('./pages/master/ms003001/ms003001.module').then(m => m.MS003001Module),
        data: { animation: 'SkillPage' },
        canActivate: [AuthGuard]
      },
      {
        path: 'skill/detail/:id',
        loadChildren: () => import('./pages/master/ms003002/ms003002.module').then(m => m.MS003002Module),
        data: { animation: 'SkillDetailPage' },
        canActivate: [AuthGuard]
      },
      {
        path: 'skill/create',
        loadChildren: () => import('./pages/master/ms003002/ms003002.module').then(m => m.MS003002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'user',
        loadChildren: () => import('./pages/master/ms001001/ms001001.module').then(m => m.MS001001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'user/detail/:id',
        loadChildren: () => import('./pages/master/ms001002/ms001002.module').then(m => m.MS001002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'user/create',
        loadChildren: () => import('./pages/master/ms001002/ms001002.module').then(m => m.MS001002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'customer',
        loadChildren: () => import('./pages/master/ms009001/ms009001.module').then(m => m.MS009001SearchModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'customer/create',
        loadChildren: () => import('./pages/master/ms009002/ms009002.module').then(m => m.MS009002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'customer/detail/:id',
        loadChildren: () => import('./pages/master/ms009002/ms009002.module').then(m => m.MS009002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'skill-level',
        loadChildren: () => import('./pages/master/ms004001/ms004001.module').then(m => m.MS004001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'skill-level/detail/:id',
        loadChildren: () => import('./pages/master/ms004002/ms004002.module').then(m => m.MS004002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'skill-level/create',
        loadChildren: () => import('./pages/master/ms004002/ms004002.module').then(m => m.MS004002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee-status',
        loadChildren: () => import('./pages/master/ms008001/ms008001.module').then(m => m.MS008001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee-status/detail/:id',
        loadChildren: () => import('./pages/master/ms008002/ms008002.module').then(m => m.MS008002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee-status/create',
        loadChildren: () => import('./pages/master/ms008002/ms008002.module').then(m => m.MS008002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee-position',
        loadChildren: () => import('./pages/master/ms007001/ms007001.module').then(m => m.MS007001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee-position/detail/:id',
        loadChildren: () => import('./pages/master/ms007002/ms007002.module').then(m => m.MS007002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'employee-position/create',
        loadChildren: () => import('./pages/master/ms007002/ms007002.module').then(m => m.MS007002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'project-position',
        loadChildren: () => import('./pages/master/ms010001/ms010001.module').then(m => m.MS010001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'project-position/detail/:id',
        loadChildren: () => import('./pages/master/ms010002/ms010002.module').then(m => m.MS010002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'project-position/create',
        loadChildren: () => import('./pages/master/ms010002/ms010002.module').then(m => m.MS010002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-type',
        loadChildren: () => import('./pages/master/ms012001/ms012001.module').then(m => m.MS012001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-type/detail/:id',
        loadChildren: () => import('./pages/master/ms012002/ms012002.module').then(m => m.MS012002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-type/create',
        loadChildren: () => import('./pages/master/ms012002/ms012002.module').then(m => m.MS012002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'holiday',
        loadChildren: () => import('./pages/master/ms013001/ms013001.module').then(m => m.MS013001SearchModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'holiday/detail/:id',
        loadChildren: () => import('./pages/master/ms013002/ms013002.module').then(m => m.MS013002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'holiday/create',
        loadChildren: () => import('./pages/master/ms013002/ms013002.module').then(m => m.MS013002DetailModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'permission',
        loadChildren: () => import('./pages/master/ms017001/ms017001.module').then(m => m.MS017001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'permission/detail/:id',
        loadChildren: () => import('./pages/master/ms017002/ms017002.module').then(m => m.MS017002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'role',
        loadChildren: () => import('./pages/master/ms018001/ms018001.module').then(m => m.MS018001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'role/create',
        loadChildren: () => import('./pages/master/ms018002/ms018002.module').then(m => m.MS018002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'role/detail/:id',
        loadChildren: () => import('./pages/master/ms018002/ms018002.module').then(m => m.MS018002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'scope-work',
        loadChildren: () => import('./pages/master/ms019001/ms019001.module').then(m => m.MS019001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'scope-work/create',
        loadChildren: () => import('./pages/master/ms019002/ms019002.module').then(m => m.MS019002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'scope-work/detail/:id',
        loadChildren: () => import('./pages/master/ms019002/ms019002.module').then(m => m.MS019002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'degree',
        loadChildren: () => import('./pages/master/ms020001/ms020001.module').then(m => m.MS020001Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'degree/create',
        loadChildren: () => import('./pages/master/ms020002/ms020002.module').then(m => m.MS020002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'degree/detail/:id',
        loadChildren: () => import('./pages/master/ms020002/ms020002.module').then(m => m.MS020002Module),
        canActivate: [AuthGuard]
      },
      {
        path: 'vacation-shift-work',
        loadChildren: () => import('./pages/master/ms021001/ms021001.module').then(m => m.MS021001Module),
        canActivate: [AuthGuard]
      },

    ]
  },

  {
    path: '**',
    redirectTo: '404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
