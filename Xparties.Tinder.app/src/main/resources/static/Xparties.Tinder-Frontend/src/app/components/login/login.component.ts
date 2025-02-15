import { Component, OnInit } from '@angular/core';

import { HlmButtonDirective } from '../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [HlmButtonDirective, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  data: any;  // To store the fetched data
  errorMessage: string = '';  // To store any error message

  constructor() { }

  ngOnInit(): void {
    // setTimeout(() => {
    //   this.fetchData();
    // }, 5000);
  }

  fetchData(): void {
    const apiUrl = 'https://www.xpartiestinder.com/api/event/events';  // Replace with your API URL

    fetch(apiUrl, {
      method: 'GET',  // or 'POST', 'PUT', etc.
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'   // Set content type for request body if needed
      },
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