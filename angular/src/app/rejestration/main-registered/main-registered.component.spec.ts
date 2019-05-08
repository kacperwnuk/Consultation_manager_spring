import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainRegisteredComponent } from './main-registered.component';

describe('MainRegisteredComponent', () => {
  let component: MainRegisteredComponent;
  let fixture: ComponentFixture<MainRegisteredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainRegisteredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainRegisteredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
