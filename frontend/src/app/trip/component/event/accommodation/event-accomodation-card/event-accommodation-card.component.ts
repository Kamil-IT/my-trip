import {Component, Input, OnInit} from '@angular/core';
import {Hotel} from "../../../../model/Hotel";
import {HotelService} from "../../../../services/HotelService";
import {TripService} from "../../../../services/TripService";

@Component({
  selector: 'app-event-accommodation-card',
  templateUrl: './event-accommodation-card.component.html',
  styleUrls: ['./event-accommodation-card.component.scss']
})
export class EventAccommodationCardComponent implements OnInit {
  @Input()
  eventId: string = ''
  @Input()
  hotel: Hotel = {
    address: {
      streetAddress: "",
      locality: ""
    },
    coordinate: {
      lat: 0,
      lon: 0
    },
    id: 0,
    name: "",
    optimizedThumbUrls: {srpDesktop: ""},
    starRating: ""
  };

  constructor(private readonly hotelService: HotelService,
              private readonly tripService: TripService) {
  }

  ngOnInit(): void {
  }

  saveHotel() {
    this.hotelService.addHotelToEvent({
      address: this.hotel.address.locality + ', ' + this.hotel.address.streetAddress,
      eventUuid: this.eventId,
      hotelName: this.hotel.name,
      hotelRating: this.hotel.starRating,
      photoUrl: this.hotel.optimizedThumbUrls.srpDesktop
    }).subscribe(() => this.tripService.populateNewTrips())
  }
}
