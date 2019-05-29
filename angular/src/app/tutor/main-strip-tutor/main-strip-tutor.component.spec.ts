import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainStripTutorComponent } from './main-strip-tutor.component';

describe('MainStripTutorComponent', () => {
  let component: MainStripTutorComponent;
  let fixture: ComponentFixture<MainStripTutorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainStripTutorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainStripTutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
