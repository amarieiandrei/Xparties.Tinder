import { Component, ElementRef, inject, OnDestroy, QueryList, signal, ViewChildren } from '@angular/core';

// animations
import { slideFromBottomToTop } from '../../../../../shared/animations/slide-bottom-to-top.animation';

// components
import { HlmSwitchComponent } from '../../../../../../../libs/ui/ui-switch-helm/src/lib/hlm-switch.component';

// directives
// import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';
import { HlmInputDirective } from '../../../../../../../libs/ui/ui-input-helm/src/lib/hlm-input.directive';
// import { HlmLabelDirective } from '../../../../../../../libs/ui/ui-label-helm/src/lib/hlm-label.directive';
import { HlmLabelDirective } from '@spartan-ng/ui-label-helm';

// icons
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faArrowLeft, faPlus, faXmark } from '@fortawesome/free-solid-svg-icons';

// imports
import { BrnSelectImports } from '@spartan-ng/brain/select';
import { HlmSelectImports } from '@spartan-ng/ui-select-helm';

// services
import { Router } from '@angular/router';
import { PhotoService } from '../../../../../core/services/photo.service';

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
  private _photoService = inject(PhotoService);

  constructor() { }

  selectedFile?: any;
  uploadedImages: any = signal<string[]>([]); // Signal to store uploaded images
  placeholders: any = signal<number[]>([1, 2, 3, 4, 5, 6]);

  // TODO: REFACTOR

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.uploadFile();
    }
  }

  uploadFile() {
    if (!this.selectedFile) {
      alert('Please select a file first.');
      return;
    }

    this._photoService.uploadPhoto(this.selectedFile).subscribe({
      next: res => {
        alert(`Upload successful: ${res}`);
        this.uploadedImages.update((images: any) => [...images, res.url]); // Add the new image URL to the signal
        this.placeholders.update((p: any) => p.toSpliced(-1, 1));
      },
      error: () => alert('Upload failed.')
    });
  }

  getAllPhotos() {
    this._photoService.getAllPhotos().subscribe(res => {
      console.log('res -> ', res);
    })
  }

  closeEditProfile() {
    this._router.navigate(['/dashboard/profile']);
  }

  ngOnDestroy(): void {
    console.log('ngOnDestroy - edit profile');
  }
}
