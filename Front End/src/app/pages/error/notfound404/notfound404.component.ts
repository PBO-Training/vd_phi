import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notfound404',
  templateUrl: './notfound404.component.html',
  styleUrls: ['./notfound404.component.scss']
})
export class Notfound404Component implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  backToHome() {
    this.router.navigateByUrl('dashboard');
  }

}
