import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

export const dashboardRoutes: Routes = [
    {
        path: '',
        component: DashboardComponent,
        children: [
            { path: '', redirectTo: 'events', pathMatch: 'full' },
            {
                path: 'events',
                loadComponent: () => import('./components/events/events.component').then(m => m.EventsComponent),
                data: { animation: 'Events' }
            },
            {
                path: 'matches',
                loadComponent: () => import('./components/matches/matches.component').then(m => m.MatchesComponent),
                data: { animation: 'Matches' }
            },
            {
                path: 'home',
                loadComponent: () => import('./components/home/home.component').then(m => m.HomeComponent),
                data: { animation: 'Home' },
                children: [
                    {
                        path: 'match-explorer',
                        loadComponent: () => import('./components/home/match-explorer/match-explorer.component').then(m => m.MatchExplorerComponent),
                        data: { animation: 'MatchExplorer', noProfileAnimation: true }
                    }
                ]
            },
            {
                path: 'chat',
                loadComponent: () => import('./components/chat/chat.component').then(m => m.ChatComponent),
                data: { animation: 'Chat' }
            },
            {
                path: 'profile',
                loadComponent: () => import('./components/profile/profile.component').then(m => m.ProfileComponent),
                data: { animation: 'Profile' },
                children: [
                    {
                        path: 'edit',
                        loadComponent: () => import('./components/profile/edit-profile/edit-profile.component').then(m => m.EditProfileComponent),
                        data: { animation: 'EditProfile', noProfileAnimation: true }
                    }
                ]
            }
        ]
    }
];
