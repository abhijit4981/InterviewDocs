import { TestBed } from '@angular/core/testing';

import { CompanyviewService } from './companyview.service';

describe('CompanyviewService', () => {
  let service: CompanyviewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompanyviewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
