import { ChangeDetectionStrategy, Component } from '@angular/core';

// modules
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faArrowRight, faCalendar, faLocationDot, faUsers } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'xpt-home',
  imports: [
    // modules
    FontAwesomeModule
  ],
  templateUrl: './home.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class HomeComponent {
  // icons
  faCalendar: IconDefinition = faCalendar;
  faLocationDot: IconDefinition = faLocationDot;
  faUsers: IconDefinition = faUsers;
  faArrowRight: IconDefinition = faArrowRight;
}
