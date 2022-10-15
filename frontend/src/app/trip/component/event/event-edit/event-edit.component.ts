import {Component, Input, OnInit} from '@angular/core';
import {Event, Property} from "../../../model/Event";
import {EventService} from "../../../services/EventService";
import {KeyValue} from "@angular/common";
import {AccommodationDetails} from "../../../model/Hotel";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss']
})
export class EventEditComponent implements OnInit {

  @Input()
  event: Event | undefined;

  locationDescription: string = '';
  from: string = '';
  to: string = '';
  title: string = '';

  constructor(readonly eventService: EventService) {
  }

  ngOnInit(): void {
    this.from = this.event?.from === undefined ? '' : this.event.from;
    this.to = this.event?.to === undefined ? '' : this.event.to;
    this.title = this.event?.title === undefined ? '' : this.event.title;
    this.locationDescription = this.event?.location.locationDescription === undefined ? '' : this.event.location.locationDescription;
  }

  getParticipantEmails(): string[] {
    if (this.event && this.event.participants) {
      return [...this.event.participants, '']
    }
    return [''];
  }

  getProperties(): Property[] {
    if (this.event?.properties) {
      let sorted = this.event?.properties
        .sort((a, b) => (a.value > b.value) ? 1 : ((b.value > a.value) ? -1 : 0));
      return [...sorted];
    }
    return []
  }

  getLinks(): Property[] {
    return this.getProperties().filter(property => !property.key.includes('weather'))
  }

  getWeatherProps(): Property[] {
    return this.getProperties().filter(property => property.key.includes('weather'))
  }

  getAccommodationDetails(): AccommodationDetails | undefined {
    let properties = this.getLinks();
    console.log(this.getProperties())
    if (properties.length > 1) {
      return {
        address: properties.filter(property => property.key.includes('hotelAddress'))[0].value,
        eventUuid: "",
        hotelName: properties.filter(property => property.key.includes('hotelName'))[0].value,
        hotelRating: properties.filter(property => property.key.includes('hotelRating'))[0].value,
        photoUrl: properties.filter(property => property.key.includes('hotelPhotoUrl'))[0].value
      }
    }
    return undefined;
  }


  remove() {
    this.eventService.removeEvent(this.event?.uuid === undefined ? '' : this.event.uuid)
  }

  save() {
    this.eventService.updateEvent(this.event?.uuid === undefined ? '' : this.event.uuid, {
      from: this.from,
      to: this.to,
      title: this.title,
      locationDescription: this.locationDescription
    });
  }

  getLocation() {
    return this.event ? this.event.location : {latitude: "", longitude: "", locationDescription: ""}
  }

  getDates() {
    return {
      from: this.event?.from ? this.event?.from : "",
      to: this.event?.to ? this.event?.to : ""
    }
  }

  getEventId(): string {
    return this.event?.uuid ? this.event.uuid : '';
  }
}
