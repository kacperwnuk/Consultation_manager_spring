import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TutorConsultationListComponent } from './tutor-consultation-list.component';

describe('TutorConsultationListComponent', () => {
  let component: TutorConsultationListComponent;
  let fixture: ComponentFixture<TutorConsultationListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TutorConsultationListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TutorConsultationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
