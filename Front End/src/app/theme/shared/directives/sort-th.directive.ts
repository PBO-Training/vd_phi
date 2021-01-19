import { Directive, EventEmitter, Output, ElementRef, HostListener, Input } from '@angular/core';
import { } from 'protractor';

@Directive({ selector: '[appDataOrder]' })
export class SortThDirective {
    @Input() sortProp: string;
    @Input() sortKeyObject: string | null;
    @Input() sortType: SortType;
    @Output() sort: EventEmitter<Sort> = new EventEmitter<Sort>();
    constructor(
        private el: ElementRef

    ) { }
    @HostListener('click') sortData() {
        const element = this.el.nativeElement as HTMLElement;
        const direction = element.getAttribute('sort-direction') as SortDirection;
        if (direction === 'desc') {
            this.sort.emit({
                sortProp: this.sortProp,
                sortType: this.sortType,
                sortDirection: direction,
                sortKeyObject: this.sortKeyObject,
            });
            element.setAttribute('sort-direction', 'asc');
            // TODO: binding class Arrow sort
            element.children[0].children[0].classList.remove("asc");
            element.children[0].children[0].classList.add("hide");
            element.children[0].children[1].classList.remove("hide");
            element.children[0].children[1].classList.add("desc");
        } else {
            this.sort.emit({
                sortProp: this.sortProp,
                sortType: this.sortType,
                sortDirection: direction,
                sortKeyObject: this.sortKeyObject,
            });
            element.setAttribute('sort-direction', 'desc');
            // TODO: binding class Arrow sort
            element.children[0].children[0].classList.remove("hide");
            element.children[0].children[0].classList.add("asc");
            element.children[0].children[1].classList.remove("desc");
            element.children[0].children[1].classList.add("hide");
        }
    }
}
export enum actionSort {
    sortDate = 'date',
    sortNumber = 'number',
    sortString = 'string',
    sortObject = 'object'
}

export type SortDirection = 'asc' | 'desc';

export type SortType = actionSort.sortDate | actionSort.sortNumber | actionSort.sortString | actionSort.sortObject;

export interface Sort {
    sortProp: string;
    sortType: SortType;
    sortKeyObject: string | null;
    sortDirection: SortDirection;
}
