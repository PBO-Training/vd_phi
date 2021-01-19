import { Directive, ElementRef, Input, OnChanges, SimpleChanges } from '@angular/core';

@Directive({
    selector: '[appProgessBar]'
})
export class ProgessBarDirective implements OnChanges {
    @Input('appProgessBar') percent: string;

    constructor(private el: ElementRef) {
    }
    ngOnChanges(changes: SimpleChanges): void {
        this.width(this.percent);
    }
    private width(value: string) {
        this.el.nativeElement.style.width = value + '%';
    }
}
