import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptConsultationComponent } from './accept-consultation.component';

describe('AcceptConsultationComponent', () => {
  let component: AcceptConsultationComponent;
  let fixture: ComponentFixture<AcceptConsultationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceptConsultationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptConsultationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
