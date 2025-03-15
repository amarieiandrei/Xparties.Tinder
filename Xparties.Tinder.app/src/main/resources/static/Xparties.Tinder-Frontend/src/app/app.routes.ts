import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthenticationGuard } from './core/guards/authentication.guard';
import { WelcomeComponent } from './components/welcome/welcome.component';

export const routes: Routes = [
    {
        path: 'welcome',
        component: WelcomeComponent,
        pathMatch: 'full'
    },
    {
        path: 'login',
        loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)
    },
    {
        path: 'dashboard',
        loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
        // TODO: Comment until the template ready
        // canActivate: [AuthenticationGuard]
    },
    {
        path: 'events',
        loadComponent: () => import('./components/events/events.component').then(m => m.EventsComponent)
    },
    {
        path: '**',
        redirectTo: 'welcome'
    }
];