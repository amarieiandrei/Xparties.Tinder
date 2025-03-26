import { Component, inject, OnDestroy } from '@angular/core';

// animations
import { slideFromBottomToTop } from '../../../../../shared/animations/slide-bottom-to-top.animation';

// components
import { HlmSwitchComponent } from '../../../../../../../libs/ui/ui-switch-helm/src/lib/hlm-switch.component';

// directives
// import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';
import { HlmInputDirective } from '../../../../../../../libs/ui/ui-input-helm/src/lib/hlm-input.directive';
import { HlmLabelDirective } from '../../../../../../../libs/ui/ui-label-helm/src/lib/hlm-label.directive';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faArrowLeft, faPlus, faXmark } from '@fortawesome/free-solid-svg-icons';

// imports
import { BrnSelectImports } from '@spartan-ng/brain/select';
import { HlmSelectImports } from '@spartan-ng/ui-select-helm';

// services
// services
import { Router } from '@angular/router';

// modules
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-profile',
  imports: [
    // components
    HlmSwitchComponent,
    // directives
    // HlmButtonDirective,
    HlmInputDirective,
    HlmLabelDirective,
    // modules
    // CommonModule,
    FontAwesomeModule,
    // imports
    BrnSelectImports,
    HlmSelectImports,
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
