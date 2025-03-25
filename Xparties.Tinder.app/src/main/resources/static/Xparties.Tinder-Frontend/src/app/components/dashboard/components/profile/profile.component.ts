import { ChangeDetectionStrategy, Component, inject, OnInit } from '@angular/core';

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
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'xpt-profile',
  imports: [
    // modules
    CommonModule,
    FontAwesomeModule,
    RouterModule,
    // components
    BrnProgressComponent,
    BrnProgressIndicatorComponent,
    // directives
    HlmProgressIndicatorDirective,
  ],
  templateUrl: './profile.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush, // ✅ Improves performance
})
// <!-- <span>Percentage of progress bar</span> -->
export class ProfileComponent implements OnInit {
  // icons
  faPen: IconDefinition = faPen;
  faCircleCheck: IconDefinition = faCircleCheck;

  // services
  private _router = inject(Router);

  value = 100;

  constructor() { }

  ngOnInit(): void {
    // setTimeout(() => (this.value = 65), 3000);
  }

  navigateToEditProfile() {
    this._router.navigate(['dashboard/profile/edit']);
  }
}