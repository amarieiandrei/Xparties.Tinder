import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'xpt-profile',
  imports: [],
  templateUrl: './profile.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class ProfileComponent { }
