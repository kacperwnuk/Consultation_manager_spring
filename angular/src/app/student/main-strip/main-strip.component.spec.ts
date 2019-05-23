import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainStripComponent } from './main-strip.component';

describe('MainStripComponent', () => {
  let component: MainStripComponent;
  let fixture: ComponentFixture<MainStripComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainStripComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainStripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
