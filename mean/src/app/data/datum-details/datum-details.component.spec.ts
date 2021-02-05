import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatumDetailsComponent } from './datum-details.component';

describe('DatumDetailsComponent', () => {
  let component: DatumDetailsComponent;
  let fixture: ComponentFixture<DatumDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatumDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatumDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
