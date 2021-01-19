import { Component, OnInit, Input } from '@angular/core';
import { Routes, Router } from '@angular/router';

@Component({
  selector: 'app-breadcrumb-share',
  templateUrl: './breadcrumb-share.component.html',
  styleUrls: ['./breadcrumb-share.component.scss']
})
export class BreadcrumbShareComponent implements OnInit {
@Input() parrentBreabcrumb: string;
@Input() childBreabcrumb: string;
@Input() parrentBreabcrumbUrl: string;
@Input() childBreabcrumbUrl: string;
  constructor(
    private route: Router
  ) { }

  ngOnInit(): void {
  }

}
