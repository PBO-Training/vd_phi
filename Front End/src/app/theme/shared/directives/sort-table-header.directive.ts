import { Directive, Input, Output, EventEmitter } from '@angular/core';

@Directive({
    // tslint:disable-next-line:directive-selector
    selector: '[th[sortable]]',
    // tslint:disable-next-line:no-host-metadata-property
    host: {
        // '[class.asc]': 'direction === "asc"',
        // '[class.desc]': 'direction === "desc"',
        '(click)': 'rotate()'
    }
})
export class SortTableDirective {
    @Input() sortable: string;
    @Input() direction: SortDirection = 'asc';
    @Output() sort = new EventEmitter<SortEvent>();

    constructor() { }

    rotate() {
        this.direction = rotate[this.direction];
        this.sort.emit({column: this.sortable, direction: this.direction});
    }
}
export type SortDirection = 'asc' | 'desc';
const rotate: {[key: string]: SortDirection} = { asc: 'desc', desc: 'asc' };

export const compare = (v1: string | number, v2: string | number) => v1 < v2 ? -1 : 1;

export interface SortEvent {
  column: string;
  direction: SortDirection;
}
