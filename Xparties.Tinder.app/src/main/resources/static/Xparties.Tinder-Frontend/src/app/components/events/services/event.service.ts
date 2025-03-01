import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../../../core/http.service';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  constructor(private http: HttpService) { }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>('/event/events');
  }
}
