import { Component, effect, inject, signal } from '@angular/core';

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
import { RouterModule } from '@angular/router';

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
})
export class DashboardComponent {
  private authenticationService = inject(AuthenticationService);

  currentUser = signal<User | null>(null);

  constructor() {
    effect(() => {
      if (this.authenticationService.isAuthenticated()) {
        this.authenticationService.getCurrentUser().subscribe(currentUser => this.currentUser.set(currentUser));
      }
    })
  }
}