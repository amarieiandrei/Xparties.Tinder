import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

// animations
import { provideAnimations } from '@angular/platform-browser/animations';

// routes
import { routes } from './app.routes';
import { provideRouter } from '@angular/router';

// modules
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// icons
import { library } from '@fortawesome/fontawesome-svg-core';
import { faFacebook, faGithub, faGoogle, faThreads } from '@fortawesome/free-brands-svg-icons';
import { faCalendar, faHeart, faUser, faBell, faComments, faXmark, faSun, faMoon, faPen, faCircleCheck, faArrowLeft, faPlus } from '@fortawesome/free-solid-svg-icons';

// Add icons to the FontAwesome library (optional, but useful for performance)
library.add(faGoogle, faFacebook, faGithub, faThreads, faXmark, faCalendar, faHeart, faComments, faUser, faBell, faSun, faMoon, faPen, faCircleCheck, faArrowLeft, faPlus);

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    FontAwesomeModule,
    provideAnimations(),
  ]
};
