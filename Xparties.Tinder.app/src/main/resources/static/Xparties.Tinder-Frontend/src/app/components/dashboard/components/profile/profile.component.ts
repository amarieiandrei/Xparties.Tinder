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
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { slideAnimation } from '../../../../shared/animations/fade.animation';

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
  animations: [slideAnimation]
})
// <!-- <span>Percentage of progress bar</span> -->
export class ProfileComponent implements OnInit {
  // icons
  faPen: IconDefinition = faPen;
  faCircleCheck: IconDefinition = faCircleCheck;

  // services
  private _router = inject(Router);
  private _route = inject(ActivatedRoute);
  value = 100;

  isEditPage = false;
  currentRoute = '';

  constructor() {
    this._router.events.subscribe(() => {
      this.isEditPage = this._router.url.endsWith('/edit');
      this.currentRoute = this.isEditPage ? 'EditProfile' : 'Profile';
    });
  }

  ngOnInit(): void {
    // setTimeout(() => (this.value = 65), 3000);
  }

  navigateToEditProfile() {
    // this._router.navigate(['dashboard/profile/edit']);
    this._router.navigate(['edit'], { relativeTo: this._route });
  }
}