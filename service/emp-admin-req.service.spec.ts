import { TestBed } from '@angular/core/testing';

import { EmpAdminReqService } from './emp-admin-req.service';

describe('EmpAdminReqService', () => {
  let service: EmpAdminReqService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpAdminReqService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
