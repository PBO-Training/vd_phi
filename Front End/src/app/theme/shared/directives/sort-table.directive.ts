import { EventEmitter, Directive, ElementRef, Input, Output, QueryList, ContentChildren, AfterContentInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { actionSort, Sort, SortThDirective } from './sort-th.directive';

@Directive({ selector: '[appSort]' })
export class SortTrTableDirective implements AfterContentInit {
    @ContentChildren(SortThDirective) elementsTh: QueryList<SortThDirective>;
    @Input() data: Array<any>;
    @Output() sorted: EventEmitter<Array<any>> = new EventEmitter<Array<any>>();
    subscription: Subscription;
    sortOrder = 1;
    private collator = new Intl.Collator(undefined, {
        numeric: true,
        sensitivity: 'base',
    });
    constructor(
        private el: ElementRef
    ) { }
    ngAfterContentInit(): void {
        this.elementsTh.map(element => {
            this.subscription = element.sort.subscribe((sort: Sort) => {
                this.sorted.emit(this.data.sort(this.startSort(sort)));
            });
        });
    }
    public startSort(sort: Sort) {
        if (sort.sortDirection === 'desc') {
            this.sortOrder = -1;
        } else {
            this.sortOrder = 1;

        }
        return (a, b) => {
            if (sort.sortType === actionSort.sortDate) {
                return this.sortData(new Date(a[sort.sortProp]), new Date(b[sort.sortProp]));
            } else if (sort.sortType === actionSort.sortObject && sort.sortKeyObject !== null) {
                return this.collator.compare(a[sort.sortProp].filter(item =>
                    item[sort.sortKeyObject] !== undefined && item[sort.sortKeyObject] !== null).map(item =>
                        item[sort.sortKeyObject].join()),
                    b[sort.sortProp].filter(item =>
                        item[sort.sortKeyObject] !== undefined && item[sort.sortKeyObject] !== null).map(item =>
                            item[sort.sortKeyObject].join())) * this.sortOrder;
            } else {
                return this.collator.compare(a[sort.sortProp], b[sort.sortProp]) * this.sortOrder;
            }
        };
    }

    private sortData(a, b) {
        if (a < b) {
            return -1 * this.sortOrder;
        } else if (a > b) {
            return 1 * this.sortOrder;
        } else {
            return 0 * this.sortOrder;
        }
    }
}

