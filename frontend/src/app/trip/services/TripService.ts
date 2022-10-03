import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TripsResponse} from "../model/Trip";
import {Observable} from "rxjs";

@Injectable()
export class TripService {

  private readonly BASE_URL = 'http://localhost:8080/v1/trip';

  private readonly trips: Observable<TripsResponse>;

  constructor(private readonly http: HttpClient) {
    this.trips = this.http.get<TripsResponse>(this.BASE_URL);

  }

  getTrips(): Observable<TripsResponse> {
    return this.trips;
  }

}
