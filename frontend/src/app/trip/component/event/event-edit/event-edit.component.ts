import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../../model/Event";
import {EventService} from "../../../services/EventService";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss']
})
export class EventEditComponent implements OnInit {

  @Input()
  event: Event | undefined;

  constructor(readonly eventService: EventService) { }

  ngOnInit(): void {
  }

}
