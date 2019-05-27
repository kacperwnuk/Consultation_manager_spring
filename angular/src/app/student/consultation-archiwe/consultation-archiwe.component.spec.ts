import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationArchiweComponent } from './consultation-archiwe.component';

describe('ConsultationArchiweComponent', () => {
  let component: ConsultationArchiweComponent;
  let fixture: ComponentFixture<ConsultationArchiweComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultationArchiweComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultationArchiweComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
