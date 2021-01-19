import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BreadcrumbShareComponent } from './breadcrumb-share.component';

describe('BreadcrumbShareComponent', () => {
  let component: BreadcrumbShareComponent;
  let fixture: ComponentFixture<BreadcrumbShareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BreadcrumbShareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BreadcrumbShareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
