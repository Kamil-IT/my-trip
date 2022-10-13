import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventWeatherTableComponent } from './event-weather-table.component';

describe('EventWeatherTableComponent', () => {
  let component: EventWeatherTableComponent;
  let fixture: ComponentFixture<EventWeatherTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventWeatherTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventWeatherTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
