import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Em001002ModalAddSkillComponent } from './em001002-modal-add-skill.component';

describe('Em001002ModalAddSkillComponent', () => {
  let component: Em001002ModalAddSkillComponent;
  let fixture: ComponentFixture<Em001002ModalAddSkillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Em001002ModalAddSkillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Em001002ModalAddSkillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
