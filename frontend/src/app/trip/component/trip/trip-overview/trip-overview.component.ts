import {Component, OnInit} from '@angular/core';
import {TripService} from "../../../services/TripService";
import {TripsResponse} from "../../../model/Trip";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../../../core/service/AuthService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-trip-overview',
  templateUrl: './trip-overview.component.html',
  styleUrls: ['./trip-overview.component.scss']
})
export class TripOverviewComponent implements OnInit {

  tripResponse: TripsResponse | undefined;

  constructor(private readonly tripService: TripService,
              private readonly http: HttpClient,
              private readonly authService: AuthService,
              private readonly router: Router) {
    this.authService.tryLogin(() => {}, () => this.router.navigate(['/login']));

    tripService.getTrips().subscribe(response => {
      this.tripResponse = response;
    });
  }

  ngOnInit(): void {
  }

}
