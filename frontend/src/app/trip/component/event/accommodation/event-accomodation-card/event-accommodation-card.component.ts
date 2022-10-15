import {Component, Input, OnInit} from '@angular/core';
import {Hotel} from "../../../../model/Hotel";

@Component({
  selector: 'app-event-accommodation-card',
  templateUrl: './event-accommodation-card.component.html',
  styleUrls: ['./event-accommodation-card.component.scss']
})
export class EventAccommodationCardComponent implements OnInit {
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

  constructor() {
  }

  ngOnInit(): void {
  }

}
