import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventAccommodationOverviewComponent } from './event-accommodation-overview.component';

describe('EventAccomodationOverviewComponent', () => {
  let component: EventAccommodationOverviewComponent;
  let fixture: ComponentFixture<EventAccommodationOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventAccommodationOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventAccommodationOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
