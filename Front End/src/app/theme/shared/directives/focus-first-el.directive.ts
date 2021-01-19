import { AfterViewInit, Directive, ElementRef, HostListener, Input, OnDestroy, Renderer2 } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subject, timer } from 'rxjs';
import { takeUntil } from 'rxjs/operators';


@Directive({
    selector: '[appFocus]'
})
export class FocusDirective implements AfterViewInit, OnDestroy {
    @Input() formGroup: NgForm;
    unsubscribe$ = new Subject();

    constructor(
        private el: ElementRef,
    ) { }

    ngOnDestroy(): void {
        this.unsubscribe$.next();
        this.unsubscribe$.complete();
    }
    ngAfterViewInit() {
        const listELement = this.el.nativeElement.querySelectorAll('.form-control-sm');
        if (listELement.length < 1) {
            return;
        }
        // tslint:disable-next-line:prefer-for-of
        for (let i = 0; i < listELement.length; i++) {
            if (!(listELement[i] as HTMLInputElement)?.readOnly) {
                (listELement[i] as HTMLElement)?.focus();
                return;
            }
        }
    }

    @HostListener('submit', ['$event'])
    public onSubmit(event): void {
        event.preventDefault();
        const time = timer(150);
        time.pipe(takeUntil(this.unsubscribe$)).subscribe(() => {
            if ('INVALID' === this.formGroup.status) {
                const formGroupInvalid = this.el.nativeElement.querySelectorAll('.is-invalid')[0] as HTMLElement;
                if (formGroupInvalid) {
                    const headerHeight = 56; /* PUT HEADER HEIGHT HERE */
                    const buffer = 56; /* MAY NOT BE NEEDED */
                    const topOfElement = window.pageYOffset + formGroupInvalid.getBoundingClientRect().top - headerHeight - buffer;
                    window.scroll({ top: topOfElement, behavior: 'smooth' });
                    // formGroupInvalid.scrollIntoView({ behavior: 'smooth', block: 'start',  inline: 'nearest' });
                    formGroupInvalid.focus();
                }
            }
        });
    }

}
