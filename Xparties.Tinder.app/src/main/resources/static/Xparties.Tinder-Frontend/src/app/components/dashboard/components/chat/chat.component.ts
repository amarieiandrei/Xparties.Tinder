import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'xpt-chat',
  imports: [],
  templateUrl: './chat.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Improves performance
})
export class ChatComponent { }
