import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
    selector: '[appResizeTerexa]'
})
export class AutoSizeDirective {
    screenHeight: number;
    constructor(
        private el: ElementRef
    ) {
        this.screenHeight = innerHeight;
    }
    @HostListener('keydown') onKeydown() {
        const elementRef = this.el.nativeElement;
        const offsetHeight = elementRef.offsetHeight;
        if (elementRef.scrollHeight) {
            if (elementRef.scrollHeight > offsetHeight && elementRef.scrollHeight < this.screenHeight - offsetHeight) {
                elementRef.style.height =  elementRef.scrollHeight + 'px';
            } else {
                elementRef.style.overflow = 'auto';
            }
        }

    }

}
