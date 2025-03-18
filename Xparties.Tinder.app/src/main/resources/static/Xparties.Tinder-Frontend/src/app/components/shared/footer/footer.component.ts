import { Component } from '@angular/core';

// modules
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faCalendar, faComments, faHeart, faUser, faXmark } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'xpt-footer',
  imports: [
    // modules
    RouterModule,
    FontAwesomeModule,
  ],
  templateUrl: './footer.component.html',
})
export class FooterComponent {
  // icons
  faXmark: IconDefinition = faXmark;
  faCalendar: IconDefinition = faCalendar;
  faHeart: IconDefinition = faHeart;
  faComments: IconDefinition = faComments;
  faUser: IconDefinition = faUser;
}
