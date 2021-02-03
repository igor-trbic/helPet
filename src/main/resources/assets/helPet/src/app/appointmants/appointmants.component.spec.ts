import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmantsComponent } from './appointmants.component';

describe('AppointmantsComponent', () => {
  let component: AppointmantsComponent;
  let fixture: ComponentFixture<AppointmantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmantsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
