import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'xpt-home',
  imports: [],
  templateUrl: './home.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class HomeComponent { }
