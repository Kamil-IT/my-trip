import {Component, Input, OnInit} from '@angular/core';
import {Event, Property} from "../../../model/Event";
import {EventService} from "../../../services/EventService";
import {KeyValue} from "@angular/common";

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

  getProperties(): Property[] | undefined {
    if (this.event?.properties) {
      console.log(this.event?.properties)
      return [...this.event?.properties];
    }
    return []
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
}
