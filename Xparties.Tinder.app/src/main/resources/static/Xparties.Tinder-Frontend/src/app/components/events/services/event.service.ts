import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../../../core/services/http.service';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private http = inject(HttpService);

  constructor() { }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>('/event/events');
  }
}