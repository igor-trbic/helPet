import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessRoleTypeComponent } from './business-role-type.component';

describe('BusinessRoleTypeComponent', () => {
  let component: BusinessRoleTypeComponent;
  let fixture: ComponentFixture<BusinessRoleTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessRoleTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessRoleTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
