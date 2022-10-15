import {Component, Input, OnInit} from '@angular/core';
import {LocationDescription} from "../../../../model/Event";
import {Observable} from "rxjs";
import {Hotel, HotelsResponse} from "../../../../model/Hotel";
import {HotelService} from "../../../../services/HotelService";

@Component({
  selector: 'app-event-accommodation-overview',
  templateUrl: './event-accommodation-overview.component.html',
  styleUrls: ['./event-accommodation-overview.component.scss']
})
export class EventAccommodationOverviewComponent implements OnInit {
  @Input()
  location: LocationDescription = {latitude: "", longitude: "", locationDescription: ""};
  @Input()
  date: { from: string, to: string } = {from: "", to: ""};
  hotels: Observable<HotelsResponse> | undefined;
  constructor(private readonly hotelService: HotelService) {
  }

  ngOnInit(): void {
    console.log(this.location)
    console.log(this.date)
    this.hotels = this.hotelService.findHotels(this.location.latitude, this.location.longitude, this.date.from, this.date.to)
    this.hotels.subscribe(res => console.log(res))
  }

}
