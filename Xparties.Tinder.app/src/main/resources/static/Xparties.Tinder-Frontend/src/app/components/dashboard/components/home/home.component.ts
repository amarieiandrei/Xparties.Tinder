import { ChangeDetectionStrategy, Component, OnInit, signal } from '@angular/core';

// components
import { HlmSkeletonComponent } from '../../../../../../libs/ui/ui-skeleton-helm/src/lib/hlm-skeleton.component';
import { HlmSpinnerComponent } from '../../../../../../libs/ui/ui-spinner-helm/src/lib/hlm-spinner.component';

// directives
import { HlmButtonDirective } from '../../../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';

// modules
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faArrowRight, faCalendar, faLocationDot, faUsers } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'xpt-home',
  imports: [
    // components
    HlmSkeletonComponent,
    HlmSpinnerComponent,
    // directives
    HlmButtonDirective,
    // modules
    CommonModule,
    FontAwesomeModule
  ],
  templateUrl: './home.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class HomeComponent implements OnInit {
  // icons
  faCalendar: IconDefinition = faCalendar;
  faLocationDot: IconDefinition = faLocationDot;
  faUsers: IconDefinition = faUsers;
  faArrowRight: IconDefinition = faArrowRight;

  isBusy = signal<boolean>(true);

  enrolledEvents = signal<any>([
    { name: 'Beach Please 2025', location: 'Costinești, Romania', date: '2025-08-18', participants: 1200, src: '../../../../assets//photos-test/beach-please2.jpeg' },
    { name: 'Beach Please 2025', location: 'Bucharest, Romania', date: '2025-08-18', participants: 1200, src: '../../../../assets//photos-test/beach-please2.jpeg' },
    { name: 'Summer Music Festival', location: 'Barcelona, Spain', date: '2025-06-15', participants: 2440, src: '../../../../assets//photos-test/beach-please2.jpeg' },
    { name: 'Summer Music Festival', location: 'Barcelona, Spain', date: '2025-07-20', participants: 12200, src: '../../../../assets//photos-test/beach-please2.jpeg' },
    { name: 'Untold Festival', location: 'Cluj-Napoca, Romania', date: '2025-08-05', participants: 18200, src: '../../../../assets//photos-test/beach-please2.jpeg' },
    { name: 'Neversea', location: 'Mamaia, Romania', date: '2025-09-10', participants: 22222, src: '../../../../assets//photos-test/beach-please2.jpeg' },
    { name: 'Neversea', location: 'Mamaia, Romania', date: '2025-09-10', participants: 22222, src: '../../../../assets//photos-test/beach-please2.jpeg' },
    { name: 'Vibe Festival', location: 'Targu Mures, Romania', date: '2025-10-03', participants: 9922, src: '../../../../assets//photos-test/snoop-dogg-dancing-exceed-limit-error-test.gif' },
  ])

  constructor() { }

  ngOnInit(): void {
    setTimeout(() => {
      this.isBusy.set(false);
    }, 2000);
  }
}
