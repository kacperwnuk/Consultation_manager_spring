import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TutorCreateConsultationComponent } from './tutor-create-consultation.component';

describe('TutorCreateConsultationComponent', () => {
  let component: TutorCreateConsultationComponent;
  let fixture: ComponentFixture<TutorCreateConsultationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TutorCreateConsultationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TutorCreateConsultationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
