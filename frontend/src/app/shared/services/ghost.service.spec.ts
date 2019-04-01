import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { GhostService } from './ghost.service';

describe('GhostService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        GhostService,
        ],
        imports: [ HttpClientTestingModule ]
    });
  });

  it('should be created', inject([HttpTestingController, GhostService],
     (httpMock: HttpTestingController, service: GhostService) => {
    expect(service).toBeTruthy();
  }));
});
