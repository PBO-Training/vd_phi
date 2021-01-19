import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002LanguageViewComponent } from './em001002-language-view.component';

describe('Em001002LanguageViewComponent', () => {
  let component: Em001002LanguageViewComponent;
  let fixture: ComponentFixture<Em001002LanguageViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002LanguageViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002LanguageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
