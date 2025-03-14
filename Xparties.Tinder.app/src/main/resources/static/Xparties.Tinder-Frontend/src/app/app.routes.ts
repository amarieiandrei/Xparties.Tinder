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
        path: 'welcome',
        loadComponent: () => import('./components/welcome/welcome.component').then(m => m.WelcomeComponent)
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
        redirectTo: ''
    }
];