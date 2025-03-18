import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'xpt-matches',
  imports: [],
  templateUrl: './matches.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class MatchesComponent {

}
