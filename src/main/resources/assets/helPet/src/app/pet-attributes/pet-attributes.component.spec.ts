import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetAttributesComponent } from './pet-attributes.component';

describe('PetAttributesComponent', () => {
  let component: PetAttributesComponent;
  let fixture: ComponentFixture<PetAttributesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PetAttributesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PetAttributesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
