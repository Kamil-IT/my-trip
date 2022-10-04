import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../../model/Event";

@Component({
  selector: 'app-event-new',
  templateUrl: './event-new.component.html',
  styleUrls: ['./event-new.component.scss']
})
export class EventNewComponent implements OnInit {

  @Input()
  creatorEmail: String | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
