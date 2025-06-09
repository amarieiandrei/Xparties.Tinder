import { Component, OnDestroy, OnInit } from '@angular/core';

// animations
import { slideFromBottomToTop } from '../../../../../shared/animations/slide-bottom-to-top.animation';

@Component({
  selector: 'xpt-match-explorer',
  imports: [],
  templateUrl: './match-explorer.component.html',
  animations: [slideFromBottomToTop]
})
export class MatchExplorerComponent implements OnInit, OnDestroy {

  constructor() { }

  ngOnInit(): void { }

  ngOnDestroy(): void {
    console.log('ngOnDestroy - match explorer');
  }
}
