import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, effect, inject, OnInit, signal } from '@angular/core';

// services
import { EventService } from './services/event.service';
import { AuthenticationService } from '../../../../core/services/authentication.service';

@Component({
  selector: 'xpt-events',
  imports: [CommonModule],
  templateUrl: './events.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class EventsComponent implements OnInit {
  private eventService = inject(EventService);
  private authenticationService = inject(AuthenticationService);

  // Refactoring to have object of page not only the array, will be usefull in the future 
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

  ngOnInit(): void { }

  loadEvents(): void {
    this.eventService.getAllEvents().subscribe(events => { this.events.set(events.content); });
  }
}