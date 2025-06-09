import { Component, inject } from '@angular/core';
import { HlmSwitchComponent } from '../../../../../libs/ui/ui-switch-helm/src/lib/hlm-switch.component';

// directives
import { HlmButtonDirective } from '../../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';
import { HlmLabelDirective } from '../../../../../libs/ui/ui-label-helm/src/lib/hlm-label.directive';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faFacebook, faGithub, faGoogle, faThreads } from '@fortawesome/free-brands-svg-icons';

// modules
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// services
import { ThemeService } from '../../../core/services/theme.service';

@Component({
  selector: 'xpt-login',
  imports: [CommonModule, FontAwesomeModule, HlmButtonDirective, HlmSwitchComponent, HlmLabelDirective],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  // icons
  faGoogle: IconDefinition = faGoogle;
  faFacebook: IconDefinition = faFacebook;
  faGithub: IconDefinition = faGithub;
  faThreads: IconDefinition = faThreads;

  private _themeService = inject(ThemeService);

  currentTheme = this._themeService.getCurrentThemeSignal();

  constructor() { }

  toggleTheme(): void {
    this._themeService.toggleTheme();
  }
}