import {Component, Input, OnDestroy, Inject, ViewEncapsulation} from '@angular/core';
import {Spinkit} from './spinkits';
import {Router, NavigationStart, NavigationEnd, NavigationCancel, NavigationError} from '@angular/router';
import {DOCUMENT} from '@angular/common';
import { SpinerService } from './spiner.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-spinner',
    templateUrl: './spinner.component.html',
    styleUrls: [
        './spinner.component.scss',
        './spinkit-css/sk-line-material.scss'
    ],
    encapsulation: ViewEncapsulation.None
})
export class SpinnerComponent implements OnDestroy {
    public isSpinnerVisible = true;
    public Spinkit = Spinkit;
    subscription: Subscription;
    @Input() public backgroundColor = '#1abc9c';
    @Input() public spinner = Spinkit.skLine;
    constructor(
        private router: Router,
        @Inject(DOCUMENT) private document: Document,
        private spinerService: SpinerService) {
        this.subscription = this.spinerService.onSpiner().subscribe( (status: boolean) => {
            status ? this.isSpinnerVisible = true : this.isSpinnerVisible = false;
        }
        );
        this.router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                this.isSpinnerVisible = true;
            } else if ( event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationError) {
                this.isSpinnerVisible = false;
            }
        }, () => {
            this.isSpinnerVisible = false;
        });
    }
    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
        this.isSpinnerVisible = false;
    }
}
