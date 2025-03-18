import { Component, inject } from '@angular/core';

// components
import { HlmSwitchComponent } from '../../../../../libs/ui/ui-switch-helm/src/lib/hlm-switch.component';

// directives
import { HlmButtonDirective } from '../../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';
import { HlmLabelDirective } from '../../../../../libs/ui/ui-label-helm/src/lib/hlm-label.directive';

// services
import { ThemeService } from '../../../core/services/theme.service';

@Component({
  selector: 'app-welcome',
  imports: [HlmButtonDirective, HlmSwitchComponent, HlmLabelDirective],
  templateUrl: './welcome.component.html',
})
export class WelcomeComponent {
  private _themeService = inject(ThemeService);

  currentTheme = this._themeService.getCurrentThemeSignal();

  toggleTheme(): void {
    this._themeService.toggleTheme();
  }
}