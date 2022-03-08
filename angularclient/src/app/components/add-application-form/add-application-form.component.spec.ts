import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddApplicationFormComponent } from './add-application-form.component';

describe('AddNoteFormComponent', () => {
  let component: AddApplicationFormComponent;
  let fixture: ComponentFixture<AddApplicationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddApplicationFormComponent ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddApplicationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
