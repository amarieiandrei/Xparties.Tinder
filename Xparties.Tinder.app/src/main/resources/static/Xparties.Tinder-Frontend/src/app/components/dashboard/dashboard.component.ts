import { Component, effect, inject, signal } from '@angular/core';

// components
import { HeaderComponent } from '../shared/header/header.component';
import { FooterComponent } from '../shared/footer/footer.component';
import { HlmSwitchComponent } from '../../../../libs/ui/ui-switch-helm/src/lib/hlm-switch.component';

// directives
import { HlmLabelDirective } from '../../../../libs/ui/ui-label-helm/src/lib/hlm-label.directive';

// interfaces
import { User } from '../../core/models/user.interface';

// services
import { AuthenticationService } from '../../core/services/authentication.service';
import { ThemeService } from '../../core/services/theme.service';

// modules
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'xpt-dashboard',
  imports: [
    // components
    HeaderComponent,
    FooterComponent,
    HlmSwitchComponent,
    // modules
    CommonModule,
    RouterModule,
    // directives
    HlmLabelDirective,
  ],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
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