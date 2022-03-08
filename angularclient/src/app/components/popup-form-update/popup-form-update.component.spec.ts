import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupFormUpdateComponent } from './popup-form-update.component';

describe('PopupFormUpdateComponent', () => {
  let component: PopupFormUpdateComponent;
  let fixture: ComponentFixture<PopupFormUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupFormUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupFormUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
