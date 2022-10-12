import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event, Property} from "../../../model/Event";
import {EventService} from "../../../services/EventService";
import {CurrentUserPrivilegesService} from "../../../../core/service/CurrentUserPrivilegesService";

@Component({
  selector: 'app-event-new',
  templateUrl: './event-new.component.html',
  styleUrls: ['./event-new.component.scss']
})
export class EventNewComponent implements OnInit {

  @Input()
  creatorEmail: String | undefined;
  @Input()
  tripId = '';

  properties: Map<string, string>[] = [];

  title = '';
  from = '';
  to = '';
  locationDescription = '';
  eventType = '';

  constructor(private readonly eventService: EventService,
              readonly currentUser: CurrentUserPrivilegesService) {
  }

  ngOnInit(): void {
  }

  save() {
    this.eventService.createEvent({
      tripId: this.tripId,
      title: this.title,
      locationDescription: this.locationDescription,
      from: this.from,
      to: this.to,
      type: this.eventType,
      properties: this.properties,
    })
    this.title = '';
    this.locationDescription = '';
    this.from = '';
    this.to = '';
    this.eventType = '';
    this.properties = [];
  }

  onPropertyAdded(property: Map<string, string>) {
    this.properties.push(property)
  }
}
