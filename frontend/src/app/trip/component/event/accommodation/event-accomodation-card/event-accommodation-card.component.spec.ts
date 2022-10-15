import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAccommodationCardComponent } from './event-accommodation-card.component';

describe('EventAccomodationCardComponent', () => {
  let component: EventAccommodationCardComponent;
  let fixture: ComponentFixture<EventAccommodationCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventAccommodationCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventAccommodationCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
