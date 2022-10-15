import {Component, Input, OnInit} from '@angular/core';
import {LocationDescription} from "../../../../model/Event";
import {Observable} from "rxjs";
import {AccommodationDetails, Hotel, HotelsResponse} from "../../../../model/Hotel";
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
  eventUuid: string = "";
  @Input()
  date: { from: string, to: string } = {from: "", to: ""};
  @Input()
  accommodationDetails: AccommodationDetails | undefined;
  hotels: Observable<HotelsResponse> | undefined;

  constructor(private readonly hotelService: HotelService) {
  }

  ngOnInit(): void {
    if (!this.accommodationDetails) {
      this.hotels = this.hotelService.findHotels(this.location.latitude, this.location.longitude, this.date.from, this.date.to)
    }
  }

  getHotel(): Hotel {
    return {
      address: {
        streetAddress: this.accommodationDetails?.address ? this.accommodationDetails?.address.split(', ')[0] : '',
        locality: this.accommodationDetails?.address ? this.accommodationDetails?.address.split(', ')[1] : ''
      },
      coordinate: {lat: -1, lon: -1},
      id: -1,
      name: this.accommodationDetails?.hotelName ? this.accommodationDetails?.hotelName : "",
      optimizedThumbUrls: {
        srpDesktop: this.accommodationDetails?.photoUrl ? this.accommodationDetails?.photoUrl : ""
      },
      starRating: this.accommodationDetails?.hotelRating ? this.accommodationDetails?.hotelRating : ""
    };
  }
}
