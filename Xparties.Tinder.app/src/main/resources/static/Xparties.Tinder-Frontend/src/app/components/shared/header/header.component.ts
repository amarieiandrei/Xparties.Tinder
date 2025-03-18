import { Component } from '@angular/core';

// modules
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faBell } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'xpt-header',
  imports: [FontAwesomeModule],
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  // icons
  faBell: IconDefinition = faBell;
}
