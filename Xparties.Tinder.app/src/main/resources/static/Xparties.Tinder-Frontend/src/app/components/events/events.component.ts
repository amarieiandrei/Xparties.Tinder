import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-events',
  imports: [CommonModule],
  templateUrl: './events.component.html',
  styleUrl: './events.component.css'
})
export class EventsComponent implements OnInit {
  data: any;  // To store the fetched data
  errorMessage: string = '';  // To store any error message

  constructor() { }

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    const apiUrl = 'https://www.xpartiestinder.com/api/event/events';  // Replace with your API URL

    fetch(apiUrl, {
      method: 'GET',  // or 'POST', 'PUT', etc.
      headers: {
        'Content-Type': 'application/json'   // Set content type for request body if needed
      },
      credentials: 'include'
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();  // Parse JSON data
      })
      .then(data => {
        this.data = data;  // Store the response data
        console.log(this.data);
      })
      .catch(error => {
        this.errorMessage = 'There was an error fetching the data: ' + error.message;
        console.error('Error:', error);
      });
  }
}
