import { TestBed } from '@angular/core/testing';

import { DatumService } from './datum.service';

describe('DatumService', () => {
  let service: DatumService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatumService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
