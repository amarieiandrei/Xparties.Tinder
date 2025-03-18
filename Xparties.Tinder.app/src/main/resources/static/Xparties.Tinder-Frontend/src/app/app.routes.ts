import { Routes } from '@angular/router';
import { AuthenticationGuard } from './core/guards/authentication.guard';
import { WelcomeComponent } from './components/authentication/welcome/welcome.component';

export const routes: Routes = [
    {
        path: 'welcome',
        component: WelcomeComponent,
        pathMatch: 'full'
    },
    {
        path: 'login',
        loadComponent: () => import('./components/authentication/login/login.component').then(m => m.LoginComponent)
    },
    {
        path: 'dashboard',
        loadChildren: () => import('./components/dashboard/dashboard.routes').then(m => m.dashboardRoutes),
        // TODO: Comment until the template ready
        // canActivate: [AuthenticationGuard]
    },
    {
        path: '**',
        redirectTo: 'welcome'
    }
];