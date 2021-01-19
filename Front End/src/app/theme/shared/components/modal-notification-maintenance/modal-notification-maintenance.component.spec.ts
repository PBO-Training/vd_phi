import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalNotificationMaintenanceComponent } from './modal-notification-maintenance.component';

describe('ModalNotificationMaintenanceComponent', () => {
  let component: ModalNotificationMaintenanceComponent;
  let fixture: ComponentFixture<ModalNotificationMaintenanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalNotificationMaintenanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalNotificationMaintenanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
