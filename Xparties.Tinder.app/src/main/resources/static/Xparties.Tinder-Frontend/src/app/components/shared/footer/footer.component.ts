import { Component } from '@angular/core';

// directives
import { RippleDirective } from '../../../shared/directives/ripple.directive';

// modules
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faCalendar, faComments, faHeart, faUser, faXmark } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'xpt-footer',
  imports: [
    // modules
    // CommonModule,
    RouterModule,
    FontAwesomeModule,
    // directives
    RippleDirective
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
