import { Component, OnInit } from '@angular/core';
import {TripService} from "../../../services/TripService";
import {Trip, TripsResponse} from "../../../model/Trip";

@Component({
  selector: 'app-trip-overview',
  templateUrl: './trip-overview.component.html',
  styleUrls: ['./trip-overview.component.scss']
})
export class TripOverviewComponent implements OnInit {

  tripResponse: TripsResponse | undefined;

  constructor(private readonly tripService: TripService) {
    tripService.getTrips().subscribe(response => {
      this.tripResponse = response;
    });
  }

  ngOnInit(): void {
  }

}
