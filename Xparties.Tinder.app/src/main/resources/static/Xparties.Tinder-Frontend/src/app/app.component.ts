import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ThemeService } from './core/services/theme.service';

@Component({
  selector: 'xpt-root',
  imports: [RouterOutlet],
  template: `<router-outlet></router-outlet>`
})
export class AppComponent implements OnInit {
  private _themeService = inject(ThemeService);

  ngOnInit(): void {
    this._themeService.getCurrentThemeSignal();
  }
}