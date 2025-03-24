import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';

// components
import { BrnProgressComponent, BrnProgressIndicatorComponent } from '@spartan-ng/brain/progress';

// directives
import { HlmProgressIndicatorDirective } from '../../../../../../libs/ui/ui-progress-helm/src/lib/hlm-progress-indicator.directive';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faCircleCheck, faPen } from '@fortawesome/free-solid-svg-icons';

// modules
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'xpt-profile',
  imports: [
    // modules
    CommonModule,
    FontAwesomeModule,
    // components
    BrnProgressComponent,
    BrnProgressIndicatorComponent,
    // directives
    HlmProgressIndicatorDirective,
  ],
  templateUrl: './profile.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
// <!-- <span>Percentage of progress bar</span> -->
export class ProfileComponent implements OnInit {
  // icons
  faPen: IconDefinition = faPen;
  faCircleCheck: IconDefinition = faCircleCheck;

  value = 100;

  constructor() { }

  ngOnInit(): void {
    // setTimeout(() => (this.value = 65), 3000);
  }

}
