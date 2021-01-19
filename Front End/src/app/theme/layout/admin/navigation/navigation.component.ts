import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NextConfig} from '../../../../app-config';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  public windowWidth: number;
  public flatConfig: any;
  // tslint:disable-next-line:no-output-on-prefix
  @Output() onNavMobCollapse = new EventEmitter();

  constructor() {
    this.flatConfig = NextConfig.config;
    this.windowWidth = innerWidth;
  }

  ngOnInit() { }

  navMobCollapse() {
    this.windowWidth = innerWidth;
    if (this.windowWidth < 992) {
      this.onNavMobCollapse.emit();
    }
  }
}
