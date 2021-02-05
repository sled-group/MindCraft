import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatumListComponent } from './datum-list.component';

describe('DatumListComponent', () => {
  let component: DatumListComponent;
  let fixture: ComponentFixture<DatumListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatumListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatumListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
