import { Component, inject, OnDestroy } from '@angular/core';

// animations
import { slideFromBottomToTop } from '../../../../../shared/animations/slide-bottom-to-top.animation';

// directives
import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';

// services
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  imports: [
    //directives
    HlmButtonDirective
  ],
  templateUrl: './edit-profile.component.html',
  animations: [slideFromBottomToTop]
})
export class EditProfileComponent implements OnDestroy {
  // services
  private router = inject(Router);

  constructor() { }

  closeEditProfile() {
    this.router.navigate(['/dashboard/profile']);
  }

  ngOnDestroy(): void {
    console.log('ngOnDestroy - edit profile');
  }
}
