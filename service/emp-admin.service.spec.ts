import { TestBed } from '@angular/core/testing';

import { EmpAdminService } from './emp-admin.service';

describe('EmpAdminService', () => {
  let service: EmpAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
