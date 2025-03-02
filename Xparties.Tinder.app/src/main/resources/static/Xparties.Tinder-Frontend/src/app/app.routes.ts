import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthenticationGuard } from './core/guards/authentication.guard';

export const routes: Routes = [
    {
        path: '',
        component: LoginComponent,
        pathMatch: 'full'
    },
    {
        path: 'dashboard',
        loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
        canActivate: [AuthenticationGuard]
    },
    {
        path: '**',
        redirectTo: ''
    }
];