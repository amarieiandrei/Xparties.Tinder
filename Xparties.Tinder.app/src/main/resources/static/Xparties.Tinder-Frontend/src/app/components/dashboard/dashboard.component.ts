import { Component, effect, inject, signal } from '@angular/core';
import { EventsComponent } from '../events/events.component';
import { AuthenticationService } from '../../core/services/authentication.service';
import { User } from '../../core/models/user.interface';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HlmButtonDirective } from '../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';

@Component({
  selector: 'xpt-dashboard',
  imports: [EventsComponent, CommonModule, HlmButtonDirective],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  private authenticationService = inject(AuthenticationService);
  private router = inject(Router);

  currentUser = signal<User | null>(null);

  constructor() {
    effect(() => {
      if (this.authenticationService.isAuthenticated()) {
        this.authenticationService.getCurrentUser().subscribe(currentUser => this.currentUser.set(currentUser));
      }
    })
  }

  navigateToEvents(): void {
    this.router.navigate(['/events']);
  }
}