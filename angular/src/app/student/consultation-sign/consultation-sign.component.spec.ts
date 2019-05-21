import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationSignComponent } from './consultation-sign.component';

describe('ConsultationSignComponent', () => {
  let component: ConsultationSignComponent;
  let fixture: ComponentFixture<ConsultationSignComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultationSignComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultationSignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
