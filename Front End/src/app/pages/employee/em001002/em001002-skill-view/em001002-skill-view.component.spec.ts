import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002SkillViewComponent } from './em001002-skill-view.component';

describe('Em001002SkillViewComponent', () => {
  let component: Em001002SkillViewComponent;
  let fixture: ComponentFixture<Em001002SkillViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002SkillViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002SkillViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
