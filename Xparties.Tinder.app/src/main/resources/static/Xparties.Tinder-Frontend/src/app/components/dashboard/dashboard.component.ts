import { Component, effect, inject, signal } from '@angular/core';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { filter } from 'rxjs';

// animations
import { slideAnimation } from '../../shared/animations/slide.animation';

// components
import { HeaderComponent } from '../shared/header/header.component';
import { FooterComponent } from '../shared/footer/footer.component';

// directives

// interfaces
import { User } from '../../core/models/user.interface';

// services
import { AuthenticationService } from '../../core/services/authentication.service';

// modules
import { CommonModule } from '@angular/common';

@Component({
  selector: 'xpt-dashboard',
  imports: [
    // components
    HeaderComponent,
    FooterComponent,
    // modules
    CommonModule,
    RouterModule,
    // directives
  ],
  templateUrl: './dashboard.component.html',
  animations: [slideAnimation]
})
export class DashboardComponent {
  // services
  private _authenticationService = inject(AuthenticationService);

  currentRoute: string = '';
  currentUser = signal<User | null>(null);

  constructor(private _router: Router) {
    effect(() => {
      if (this._authenticationService.isAuthenticated()) {
        this._authenticationService.getCurrentUser().subscribe(currentUser => this.currentUser.set(currentUser));
      }
    });

    this._router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      const url = event.urlAfterRedirects;

      if (!url.includes('profile/edit') && !url.includes('home/match-explorer')) {
        this.currentRoute = url.split('/').pop() || '';
      }
    });
  }
}