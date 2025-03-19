import { Component, CUSTOM_ELEMENTS_SCHEMA, inject } from '@angular/core';

// components
import { HlmSwitchComponent } from '../../../../../libs/ui/ui-switch-helm/src/lib/hlm-switch.component';

// directives
import { HlmLabelDirective } from '../../../../../libs/ui/ui-label-helm/src/lib/hlm-label.directive';

// modules
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faBell } from '@fortawesome/free-solid-svg-icons';

// services
import { ThemeService } from '../../../core/services/theme.service';

@Component({
  selector: 'xpt-header',
  imports: [
    // modules
    FontAwesomeModule,
    // components
    HlmSwitchComponent,
    // directives
    HlmLabelDirective,
  ],
  templateUrl: './header.component.html',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HeaderComponent {
  // icons
  faBell: IconDefinition = faBell;

  // services
  private _themeService = inject(ThemeService);

  currentTheme = this._themeService.getCurrentThemeSignal();

  toggleTheme(): void {
    this._themeService.toggleTheme();
  }
}