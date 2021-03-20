import { TestBed } from '@angular/core/testing';

import { BusinessRoleTypeService } from './business-role-type.service';

describe('BusinessRoleTypeService', () => {
  let service: BusinessRoleTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessRoleTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
