import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AddParticipantModel, CreateTripRequestModel, Trip, TripsResponse, UpdateTripRequestModel} from "../model/Trip";
import {BehaviorSubject, map, Observable} from "rxjs";
import {ParticipantManagment} from "./ParticipantManagment";

@Injectable()
export class TripService implements ParticipantManagment {

  private readonly BASE_URL = 'http://localhost:8080/v1/trip';

  private readonly tripsSubject = new BehaviorSubject<TripsResponse>({trips: []});

  constructor(private readonly http: HttpClient) {
  }

  getAllParticipantsEmail(parentId: string): Observable<string[]> {
    return this.getTrip(parentId).pipe(map((trip: Trip) => trip.participantsEmails));
  }

  getTrips(): Observable<TripsResponse> {
    this.populateNewTrips()
    return this.tripsSubject.asObservable();
  }

  getTripById(tripId: string): Observable<Trip> {
    let subjectTrip = new BehaviorSubject<Trip>({
      creatorEmail: "",
      events: [],
      from: "",
      participantsEmails: [],
      title: "",
      to: "",
      uuid: ""
    });

    // Request trip by id
    let trip = this.getTrip(tripId);
    trip.subscribe(res => subjectTrip.next(res))

    // Refresh trip when another hanged
    this.tripsSubject.asObservable()
      .subscribe(() =>
        this.getTrip(tripId)
          .subscribe(res => subjectTrip.next(res)))

    return subjectTrip.asObservable();
  }

  private getTrip(tripId: string) {
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

  addParticipant(addParticipantModel: AddParticipantModel): void {
    // Custom response for error
    let tripsResponse = this.http.post<TripsResponse>(this.BASE_URL + '/participant', addParticipantModel);
    tripsResponse.subscribe(() => this.populateNewTrips())
  }

  removeParticipant(addParticipantModel: AddParticipantModel): void {
    // Custom response for error
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: addParticipantModel,
    };
    let tripsResponse = this.http.delete<TripsResponse>(this.BASE_URL + '/participant', options);
    tripsResponse.subscribe(() => this.populateNewTrips())
  }

  populateNewTrips(): void {
    this.http.get<TripsResponse>(this.BASE_URL).subscribe(res => {
      this.tripsSubject.next(res);
    })
  }

}
