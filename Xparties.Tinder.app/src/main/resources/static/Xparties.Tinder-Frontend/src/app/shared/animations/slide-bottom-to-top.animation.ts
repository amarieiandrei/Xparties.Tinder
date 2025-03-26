import { animate, style, transition, trigger } from '@angular/animations';

export const slideFromBottomToTop = trigger('slideFromBottomToTop', [
    transition(':enter', [
        style({ transform: 'translateY(10%)', opacity: 0 }),
        animate('150ms ease-out', style({ transform: 'translateY(0)', opacity: 1 }))
    ])
]);