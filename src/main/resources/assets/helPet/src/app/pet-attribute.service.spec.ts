import { TestBed } from '@angular/core/testing';

import { PetAttributeService } from './pet-attribute.service';

describe('PetAttributeService', () => {
  let service: PetAttributeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetAttributeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
