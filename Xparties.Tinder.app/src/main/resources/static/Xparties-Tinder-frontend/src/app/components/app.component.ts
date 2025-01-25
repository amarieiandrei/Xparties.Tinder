import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  template: `<router-outlet></router-outlet>`,
})
// The container where Angular loads all the components based on the SSR or CSR routes ( router-outlet, a directive for this scope )
export class AppComponent {}
