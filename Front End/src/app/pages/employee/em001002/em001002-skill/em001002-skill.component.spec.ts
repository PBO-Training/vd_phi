import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002SkillComponent } from './em001002-skill.component';

describe('Em001002SkillComponent', () => {
  let component: Em001002SkillComponent;
  let fixture: ComponentFixture<Em001002SkillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002SkillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002SkillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
