import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AddParticipantModel, CreateTripRequestModel, Trip, TripsResponse, UpdateTripRequestModel} from "../model/Trip";
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

  updateTrip(requestModel: UpdateTripRequestModel): Observable<TripsResponse> {
    // Custom response for error
    return this.http.put<TripsResponse>(this.BASE_URL, requestModel);
  }

  addParticipant(addParticipantModel: AddParticipantModel): Observable<TripsResponse> {
    // Custom response for error
    return this.http.post<TripsResponse>(this.BASE_URL + '/participant', addParticipantModel);
  }

  removeParticipant(addParticipantModel: AddParticipantModel): Observable<TripsResponse> {
    // Custom response for error
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: addParticipantModel,
    };
    return this.http.delete<TripsResponse>(this.BASE_URL + '/participant', options);
  }

}
