import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002LanguageComponent } from './em001002-language.component';

describe('Em001002LanguageComponent', () => {
  let component: Em001002LanguageComponent;
  let fixture: ComponentFixture<Em001002LanguageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002LanguageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002LanguageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
