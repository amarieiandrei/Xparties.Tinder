import { Component, effect, inject, signal } from '@angular/core';

// components
import { HlmSwitchComponent } from '../../../../libs/ui/ui-switch-helm/src/lib/hlm-switch.component';
import { EventsComponent } from '../events/events.component';

// directives
import { HlmButtonDirective } from '../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';
import { HlmLabelDirective } from '../../../../libs/ui/ui-label-helm/src/lib/hlm-label.directive';

// interfaces
import { User } from '../../core/models/user.interface';

// services
import { AuthenticationService } from '../../core/services/authentication.service';
import { ThemeService } from '../../core/services/theme.service';

// modules
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faBell, faCalendar, faComments, faHeart, faUser, faXmark } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'xpt-dashboard',
  imports: [EventsComponent, CommonModule, FontAwesomeModule, HlmButtonDirective, HlmSwitchComponent, HlmLabelDirective],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
  // icons
  // header
  faBell: IconDefinition = faBell;
  // footbar
  faXmark: IconDefinition = faXmark;
  faCalendar: IconDefinition = faCalendar;
  faHeart: IconDefinition = faHeart;
  faComments: IconDefinition = faComments;
  faUser: IconDefinition = faUser;

  private authenticationService = inject(AuthenticationService);
  private _themeService = inject(ThemeService);

  currentUser = signal<User | null>(null);
  currentTheme = this._themeService.getCurrentThemeSignal();

  constructor() {
    effect(() => {
      if (this.authenticationService.isAuthenticated()) {
        this.authenticationService.getCurrentUser().subscribe(currentUser => this.currentUser.set(currentUser));
      }
    })
  }

  toggleTheme(): void {
    this._themeService.toggleTheme();
  }
}