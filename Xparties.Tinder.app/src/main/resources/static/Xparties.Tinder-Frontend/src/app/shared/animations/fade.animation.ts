import { animate, style, transition, trigger } from '@angular/animations';

export const slideAnimation = trigger('routeAnimations', [
    transition('* <=> *', [
        style({ opacity: 0, transform: 'translateX(5%)' }),
        animate('500ms ease-out', style({ opacity: 1, transform: 'translateX(0)' }))
    ]),
]);