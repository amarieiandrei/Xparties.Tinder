import { Component, inject, OnDestroy, signal } from '@angular/core';

// animations
import { slideFromBottomToTop } from '../../../../../shared/animations/slide-bottom-to-top.animation';

// directives
import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faArrowLeft, faPlus, faXmark } from '@fortawesome/free-solid-svg-icons';

// services
import { Router } from '@angular/router';

// modules
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-profile',
  imports: [
    // directives
    HlmButtonDirective,
    // modules
    // CommonModule,
    FontAwesomeModule
  ],
  templateUrl: './edit-profile.component.html',
  animations: [slideFromBottomToTop]
})
export class EditProfileComponent implements OnDestroy {
  // icons
  faArrowLeft: IconDefinition = faArrowLeft;
  faPlus: IconDefinition = faPlus;
  faXmark: IconDefinition = faXmark;

  // services
  private _router = inject(Router);

  constructor() { }

  closeEditProfile() {
    this._router.navigate(['/dashboard/profile']);
  }

  ngOnDestroy(): void {
    console.log('ngOnDestroy - edit profile');
  }
}
