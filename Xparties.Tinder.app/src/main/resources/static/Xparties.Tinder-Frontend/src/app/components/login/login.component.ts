import { Component } from '@angular/core';

import { HlmButtonDirective } from '../../../../libs/ui/ui-button-helm/src/lib/hlm-button.directive';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [HlmButtonDirective, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent { }