import { Component, effect, inject, signal } from '@angular/core';
import { EventsComponent } from '../events/events.component';
import { AuthenticationService } from '../../core/services/authentication.service';
import { User } from '../../core/models/user.interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'xpt-dashboard',
  imports: [EventsComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
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