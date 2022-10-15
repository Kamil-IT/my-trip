import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreateEvent} from "../model/Event";
import {ParticipantManagment} from "./ParticipantManagment";
import {HeaderService} from "../../core/service/HeaderService";
import {AddAccommodationDto, Hotel, HotelsResponse} from "../model/Hotel";

@Injectable()
export class HotelService {

  private readonly BASE_URL = 'http://localhost:8080/v1';

  constructor(private readonly http: HttpClient,
              private readonly headerService: HeaderService) {
  }

  findHotels(latitude: string, longitude: string, from: string, to: string, adultsNumber = 1): Observable<HotelsResponse> {
    // Custom response for error
    return this.http.post<HotelsResponse>(this.BASE_URL + '/hotel',
      {
        latitude: longitude,
        longitude: latitude,
        from: from,
        to: to,
        adultsNumber: adultsNumber
      },
      {headers: this.headerService.getHeaders()});
  }

  addHotelToEvent(body: AddAccommodationDto): Observable<void> {
    // Custom response for error
    return this.http.post<void>(this.BASE_URL + '/trip/event/accommodation/property',
      body, {headers: this.headerService.getHeaders()});
  }
}
