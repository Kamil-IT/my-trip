import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripItemComponent } from './trip-item.component';

describe('TripItemComponent', () => {
  let component: TripItemComponent;
  let fixture: ComponentFixture<TripItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TripItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
