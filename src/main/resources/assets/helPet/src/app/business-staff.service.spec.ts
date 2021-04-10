import { TestBed } from '@angular/core/testing';

import { BusinessStaffService } from './business-staff.service';

describe('BusinessStaffService', () => {
  let service: BusinessStaffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessStaffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
