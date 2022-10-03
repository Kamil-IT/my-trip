import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {CreateTripRequestModel, Trip, TripsResponse} from "../model/Trip";
import {Observable} from "rxjs";

@Injectable()
export class TripService {

  private readonly BASE_URL = 'http://localhost:8080/v1/trip';

  private readonly getTripsObs: Observable<TripsResponse>;

  constructor(private readonly http: HttpClient) {
    this.getTripsObs = this.http.get<TripsResponse>(this.BASE_URL);

  }

  getTrips(): Observable<TripsResponse> {
    return this.getTripsObs;
  }

  getTripById(tripId: string): Observable<Trip> {
    return this.http.get<Trip>(this.BASE_URL + '/' + tripId);
  }

  createTrip(requestModel: CreateTripRequestModel): Observable<TripsResponse> {
    // Custom response for error
    return this.http.post<TripsResponse>(this.BASE_URL, requestModel);
  }

}
