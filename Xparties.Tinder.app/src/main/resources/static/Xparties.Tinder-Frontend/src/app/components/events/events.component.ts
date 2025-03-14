import { CommonModule } from '@angular/common';
import { Component, effect, inject, OnInit, signal } from '@angular/core';

// directives
import { HlmButtonDirective } from '../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';

// services
import { EventService } from './services/event.service';
import { AuthenticationService } from '../../core/services/authentication.service';

@Component({
  selector: 'xpt-events',
  imports: [HlmButtonDirective, CommonModule],
  templateUrl: './events.component.html',
  styleUrl: './events.component.css'
})
export class EventsComponent implements OnInit {
  private eventService = inject(EventService);
  private authenticationService = inject(AuthenticationService);

  events = signal<Event[]>([]);

  constructor() {
    effect(() => {
      if (this.authenticationService.isAuthenticated()) {
        this.loadEvents();
      }
    })
  }

  ngOnInit(): void { }

  loadEvents(): void {
    this.eventService.getAllEvents().subscribe(events => this.events.set(events));
  }
}
