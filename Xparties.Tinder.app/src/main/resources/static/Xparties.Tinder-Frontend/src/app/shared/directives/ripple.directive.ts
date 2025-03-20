import { Directive, HostListener, Renderer2, ElementRef } from '@angular/core';

@Directive({
    selector: '[xptRipple]',
})
export class RippleDirective {
    constructor(private renderer: Renderer2, private el: ElementRef) { }

    @HostListener('click', ['$event'])
    onClick(event: MouseEvent) {
        let target = event.target as HTMLElement;

        // If the clicked element is an `div`, or `button`, use it directly
        if (['DIV', 'BUTTON'].includes(target.tagName)) {
            target = target;
        }
        // If the clicked element is inside an `fa-icon`, use its parent
        else if (target.closest('fa-icon')) {
            target = target.closest('fa-icon')!.parentElement!;
        }
        // If the clicked element is inside an `img`, use its parent
        else if (target.closest('img')) {
            target = target.closest('img')!.parentElement!;
        }
        // Otherwise, use the element the directive is applied to
        else {
            target = this.el.nativeElement;
        }

        // Ensure the target has `position: relative` to contain the ripple
        if (window.getComputedStyle(target).position !== 'relative') {
            this.renderer.setStyle(target, 'position', 'relative');
            this.renderer.setStyle(target, 'overflow', 'hidden');
        }

        // Create the ripple element
        const ripple = this.renderer.createElement('span');
        this.renderer.addClass(ripple, 'ripple-effect');

        // Calculate ripple position and size
        const rect = target.getBoundingClientRect();
        const diameter = Math.max(target.clientWidth, target.clientHeight);
        const radius = diameter / 2;
        const x = event.clientX - rect.left - radius;
        const y = event.clientY - rect.top - radius;

        // Apply styles to the ripple
        this.renderer.setStyle(ripple, 'width', `${diameter}px`);
        this.renderer.setStyle(ripple, 'height', `${diameter}px`);
        this.renderer.setStyle(ripple, 'left', `${x}px`);
        this.renderer.setStyle(ripple, 'top', `${y}px`);

        // Append the ripple to the target element
        this.renderer.appendChild(target, ripple);

        // Remove the ripple after the animation ends
        setTimeout(() => {
            this.renderer.removeChild(target, ripple);
        }, 600);
    }
}