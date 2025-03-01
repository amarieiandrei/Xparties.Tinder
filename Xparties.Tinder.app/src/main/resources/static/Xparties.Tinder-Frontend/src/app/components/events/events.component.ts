import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { HlmButtonDirective } from '../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';
import { EventService } from './services/event.service';

@Component({
  selector: 'xpt-events',
  imports: [HlmButtonDirective, CommonModule],
  templateUrl: './events.component.html',
  styleUrl: './events.component.css'
})
export class EventsComponent implements OnInit {
  events = signal<Event[]>([]);

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.eventService.getAllEvents().subscribe(events => this.events.set(events));
  }
}
