import { animate, AUTO_STYLE, state, style, transition, trigger, group, query, animateChild } from '@angular/animations';

export const collapse =
  trigger('collapse', [
    state('collapsed, void',
      style({
        overflow: 'hidden',
        height: '0px',
      })
    ),
    state('expanded',
      style({
        overflow: 'hidden',
        height: AUTO_STYLE,
      })
    ),
    transition('collapsed <=> expanded', [
      animate('400ms ease-in-out')
    ])
  ]);
export const slideInAnimation2 =
  trigger('routeAnimations', [
    transition('HomePage <=> AboutPage', [
      style({ position: 'relative' }),
      query(':enter, :leave', [
        style({
          position: 'absolute',
          top: 0,
          left: 0,
          width: '100%'
        })
      ]),
      query(':enter', [
        style({ left: '-100%' })
      ]),
      query(':leave', animateChild()),
      group([
        query(':leave', [
          animate('600ms ease-out', style({ left: '100%' }))
        ]),
        query(':enter', [
          animate('600ms ease-out', style({ left: '0%' }))
        ])
      ]),
      query(':enter', animateChild()),
    ]),
    transition('* <=> FilterPage', [
      style({ position: 'relative' }),
      query(':enter, :leave', [
        style({
          position: 'absolute',
          top: 0,
          left: 0,
          width: '100%'
        })
      ]),
      query(':enter', [
        style({ left: '-100%' })
      ]),
      query(':leave', animateChild()),
      group([
        query(':leave', [
          animate('200ms ease-out', style({ left: '100%' }))
        ]),
        query(':enter', [
          animate('300ms ease-out', style({ left: '0%' }))
        ])
      ]),
      query(':enter', animateChild()),
    ])
  ]);

export const slideInAnimation =
  trigger('routeAnimations', [
    transition('* <=> *', [
      style({ opacity: 0 }),
      animate(200, style({ opacity: 1 }))
    ])
  ]);
export const rotate =
  trigger('rotate', [
    state('hide', style({ transform: 'rotate(0)' })),
    state('show', style({ transform: 'rotate(-180deg)' })),
    transition('hide => show', animate('400ms ease-out')),
    transition('show => hide', animate('400ms ease-in'))
  ]);
