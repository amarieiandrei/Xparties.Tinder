import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

export const dashboardRoutes: Routes = [
    {
        path: '',
        component: DashboardComponent,
        children: [
            { path: '', redirectTo: 'events', pathMatch: 'full' },
            { path: 'events', loadComponent: () => import('./components/events/events.component').then(m => m.EventsComponent) },
            { path: 'matches', loadComponent: () => import('./components/matches/matches.component').then(m => m.MatchesComponent) },
            { path: 'home', loadComponent: () => import('./components/home/home.component').then(m => m.HomeComponent) },
            { path: 'chat', loadComponent: () => import('./components/chat/chat.component').then(m => m.ChatComponent) },
            { path: 'profile', loadComponent: () => import('./components/profile/profile.component').then(m => m.ProfileComponent) }
        ]
    }
];
