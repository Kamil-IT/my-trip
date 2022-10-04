import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AddParticipantModel, CreateTripRequestModel, Trip, TripsResponse, UpdateTripRequestModel} from "../model/Trip";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable()
export class TripService {

  private readonly BASE_URL = 'http://localhost:8080/v1/trip';

  private readonly tripsSubject = new BehaviorSubject<TripsResponse>({trips: []});

  constructor(private readonly http: HttpClient) {}

  getTrips(): Observable<TripsResponse> {
    this.populateNewTrips()
    return this.tripsSubject.asObservable();
  }

  getTripById(tripId: string): Observable<Trip> {
    return this.http.get<Trip>(this.BASE_URL + '/' + tripId);
  }

  createTrip(requestModel: CreateTripRequestModel): Observable<TripsResponse> {
    // Custom response for error
    let tripsResponse = this.http.post<TripsResponse>(this.BASE_URL, requestModel);
    tripsResponse.subscribe(() => this.populateNewTrips())
    return tripsResponse;
  }

  updateTrip(requestModel: UpdateTripRequestModel): Observable<TripsResponse> {
    // Custom response for error
    let tripsResponse = this.http.put<TripsResponse>(this.BASE_URL, requestModel);
    tripsResponse.subscribe(() => this.populateNewTrips())
    return tripsResponse;
  }

  addParticipant(addParticipantModel: AddParticipantModel): Observable<TripsResponse> {
    // Custom response for error
    let tripsResponse = this.http.post<TripsResponse>(this.BASE_URL + '/participant', addParticipantModel);
    tripsResponse.subscribe(() => this.populateNewTrips())
    return tripsResponse;
  }

  removeParticipant(addParticipantModel: AddParticipantModel): Observable<TripsResponse> {
    // Custom response for error
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: addParticipantModel,
    };
    let tripsResponse = this.http.delete<TripsResponse>(this.BASE_URL + '/participant', options);
    tripsResponse.subscribe(() => this.populateNewTrips())
    return tripsResponse;
  }

  populateNewTrips(): void {
    this.http.get<TripsResponse>(this.BASE_URL).subscribe(res => {
      this.tripsSubject.next(res);
    })
  }

}
