import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReferenceMaterialComponent } from './reference-material.component';

describe('ReferenceMaterialComponent', () => {
  let component: ReferenceMaterialComponent;
  let fixture: ComponentFixture<ReferenceMaterialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReferenceMaterialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReferenceMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
