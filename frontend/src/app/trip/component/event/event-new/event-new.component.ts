import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event, Property} from "../../../model/Event";
import {EventService} from "../../../services/EventService";

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

  constructor(private readonly eventService: EventService) {
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
  }

  onPropertyAdded(property: Map<string, string>) {
    this.properties.push(property)
  }
}
