import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, effect, inject, OnInit, signal } from '@angular/core';

// services
import { EventService } from './services/event.service';
import { AuthenticationService } from '../../../../core/services/authentication.service';

// directives
import { HlmButtonDirective } from '../../../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';
import { HlmSkeletonComponent } from '@spartan-ng/ui-skeleton-helm';

@Component({
  selector: 'xpt-events',
  imports: [
    // modules
    CommonModule,
    // directives
    HlmButtonDirective,
    // components
    HlmSkeletonComponent,
  ],
  templateUrl: './events.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class EventsComponent implements OnInit {
  private eventService = inject(EventService);
  private authenticationService = inject(AuthenticationService);

  isBusy = signal<boolean>(true);

  // TODO: Remove, only for testing purpose 
  events2 = signal<any>([]);
  events = signal<any>([
    { name: 'Beach Please 2025', category: 'Music', date: '2025-08-18' },
    { name: 'Beach Please 2025', category: 'Music', date: '2025-08-18' },
    { name: 'Tech Conference 2025', category: 'Technology', date: '2025-06-15' },
    { name: 'Summer Music Festival', category: 'Music', date: '2025-07-20' },
    { name: 'Startup Pitch Night', category: 'Business', date: '2025-08-05' },
    { name: 'Art Exhibition Opening', category: 'Art', date: '2025-09-10' },
    { name: 'Marathon Charity Run', category: 'Sports', date: '2025-10-03' },
    { name: 'Tech Conference 2025', category: 'Technology', date: '2025-06-15' },
    { name: 'Summer Music Festival', category: 'Music', date: '2025-07-20' },
    { name: 'Startup Pitch Night', category: 'Business', date: '2025-08-05' },
    { name: 'Art Exhibition Opening', category: 'Art', date: '2025-09-10' },
    { name: 'Marathon Charity Run', category: 'Sports', date: '2025-10-03' },
    { name: 'Tech Conference 2025', category: 'Technology', date: '2025-06-15' },
    { name: 'Summer Music Festival', category: 'Music', date: '2025-07-20' },
    { name: 'Startup Pitch Night', category: 'Business', date: '2025-08-05' },
    { name: 'Art Exhibition Opening', category: 'Art', date: '2025-09-10' },
    { name: 'Marathon Charity Run', category: 'Sports', date: '2025-10-03' },
    { name: 'Tech Conference 2025', category: 'Technology', date: '2025-06-15' },
    { name: 'Summer Music Festival', category: 'Music', date: '2025-07-20' },
    { name: 'Startup Pitch Night', category: 'Business', date: '2025-08-05' },
    { name: 'Art Exhibition Opening', category: 'Art', date: '2025-09-10' },
    { name: 'Marathon Charity Run', category: 'Sports', date: '2025-10-03' },
    { name: 'Tech Conference 2025', category: 'Technology', date: '2025-06-15' },
    { name: 'Summer Music Festival', category: 'Music', date: '2025-07-20' },
    { name: 'Startup Pitch Night', category: 'Business', date: '2025-08-05' },
    { name: 'Art Exhibition Opening', category: 'Art', date: '2025-09-10' },
    { name: 'Marathon Charity Run', category: 'Sports', date: '2025-10-03' },
  ]);

  constructor() {
    // effect(() => {
    //   if (this.authenticationService.isAuthenticated()) {
    //     this.loadEvents();
    //   }
    // })
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.isBusy.set(false);
    }, 2000);
  }

  loadEvents(): void {
    this.eventService.getAllEvents().subscribe({
      next: events => {
        this.events.set(events?.content);
        this.isBusy.set(false);
      },
      error: () => this.isBusy.set(false)
    });
  }
}