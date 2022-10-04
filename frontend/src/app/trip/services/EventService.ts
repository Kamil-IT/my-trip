import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AddParticipantModel, CreateTripRequestModel, Trip, TripsResponse, UpdateTripRequestModel} from "../model/Trip";
import {BehaviorSubject, Observable} from "rxjs";
import {CreateEvent, Event} from "../model/Event";
import {TripService} from "./TripService";

@Injectable()
export class EventService {

  private readonly BASE_URL = 'http://localhost:8080/v1/trip/event';

  constructor(private readonly http: HttpClient,
              private readonly eventService: TripService) {}

  createEvent(requestModel: CreateEvent): Observable<Event> {
    // Custom response for error
    let tripsResponse = this.http.post<Event>(this.BASE_URL, requestModel);
    tripsResponse.subscribe(() => this.populateNewTrips())
    return tripsResponse;
  }

  // updateEvent(requestModel: UpdateTripRequestModel): Observable<TripsResponse> {
  //   // Custom response for error
  //   let tripsResponse = this.http.put<TripsResponse>(this.BASE_URL, requestModel);
  //   tripsResponse.subscribe(() => this.populateNewTrips())
  //   return tripsResponse;
  // }
  //
  // removeEvent(addParticipantModel: AddParticipantModel): Observable<TripsResponse> {
  //   // Custom response for error
  //   const options = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json',
  //     }),
  //     body: addParticipantModel,
  //   };
  //   let tripsResponse = this.http.delete<TripsResponse>(this.BASE_URL + '/participant', options);
  //   tripsResponse.subscribe(() => this.populateNewTrips())
  //   return tripsResponse;
  // }

  private populateNewTrips(): void {
    this.eventService.populateNewTrips();
  }

}
