import { Directive, HostListener, Renderer2, ElementRef } from '@angular/core';

@Directive({
    selector: '[xptRipple]'
})
export class RippleDirective {
    constructor(private renderer: Renderer2, private el: ElementRef) { }

    @HostListener('click', ['$event'])
    onClick(event: MouseEvent) {
        let target = event.target as HTMLElement;

        // Check if the clicked element is an `fa-icon` or inside an `fa-icon`
        if (target.closest('fa-icon')) {
            target = target.closest('fa-icon')!.parentElement!; // Use the parent of the `fa-icon`
        } else if (target.closest('img')) {
            target = target.closest('img')!.parentElement!;
        }

        // Ensure the clicked element is a child of the container
        if (target !== this.el.nativeElement) {
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

            // Append the ripple to the clicked element
            this.renderer.appendChild(target, ripple);

            // Remove the ripple after the animation ends
            setTimeout(() => {
                this.renderer.removeChild(target, ripple);
            }, 600);
        }
    }
}