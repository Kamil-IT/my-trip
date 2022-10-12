import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AddParticipantModel, Trip, TripsResponse} from "../model/Trip";
import {BehaviorSubject, map, Observable} from "rxjs";
import {CreateEvent, Event, UpdateEvent} from "../model/Event";
import {TripService} from "./TripService";
import {ParticipantManagment} from "./ParticipantManagment";
import {HeaderService} from "../../core/service/HeaderService";

@Injectable()
export class EventService implements ParticipantManagment {

  private readonly BASE_URL = 'http://localhost:8080/v1/trip/event';

  constructor(private readonly http: HttpClient,
              private readonly eventService: TripService,
              private readonly headerService: HeaderService) {}

  createEvent(requestModel: CreateEvent): Observable<Event> {
    // Custom response for error
    let tripsResponse = this.http.post<Event>(this.BASE_URL, requestModel, {headers: this.headerService.getHeaders()});
    tripsResponse.subscribe(() => this.populateNewTrips())
    return tripsResponse;
  }

  private populateNewTrips(): void {
    this.eventService.populateNewTrips();
  }

  addParticipant(addParticipantModel: AddParticipantModel): void {
    // Custom response for error
    let tripsResponse = this.http.post<TripsResponse>(this.BASE_URL + '/participant', addParticipantModel, {headers: this.headerService.getHeaders()});
    tripsResponse.subscribe(() => this.populateNewTrips())
  }

  removeParticipant(addParticipantModel: AddParticipantModel): void {
    // Custom response for error
    const options = {
      headers: this.headerService.getHeaders(),
      body: addParticipantModel,
    };
    let tripsResponse = this.http.delete<TripsResponse>(this.BASE_URL + '/participant', options);
    tripsResponse.subscribe(() => this.populateNewTrips())
  }

  private getTripById(eventId: string): Observable<Event> {
    return this.http.get<Event>(this.BASE_URL + '/' + eventId, {headers: this.headerService.getHeaders()});
  }

  getAllParticipantsEmail(parentId: string): Observable<string[]> {
    return this.getTripById(parentId).pipe(map((trip: Event) => trip.participants));
  }

  removeEvent(eventId: string) {
    this.http.delete<Event>(this.BASE_URL + '/' + eventId, {headers: this.headerService.getHeaders()})
      .subscribe(() => this.populateNewTrips());
  }

  updateEvent(eventId: string, updateEvent: UpdateEvent) {
    this.http.post<Event>(this.BASE_URL + '/' + eventId, updateEvent, {headers: this.headerService.getHeaders()})
      .subscribe(() => this.populateNewTrips())
  }
}
