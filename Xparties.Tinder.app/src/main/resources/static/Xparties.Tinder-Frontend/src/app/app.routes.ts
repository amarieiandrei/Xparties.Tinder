import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    {
        path: '',
        component: LoginComponent,
        pathMatch: 'full'
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
