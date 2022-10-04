import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripParticipantComponent } from './trip-participant.component';

describe('TripParticipantComponent', () => {
  let component: TripParticipantComponent;
  let fixture: ComponentFixture<TripParticipantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TripParticipantComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripParticipantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
