import { Component } from '@angular/core';
import { EventsComponent } from '../events/events.component';

@Component({
  selector: 'xpt-dashboard',
  imports: [EventsComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
