import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../../model/Event";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss']
})
export class EventEditComponent implements OnInit {

  @Input()
  event: Event | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
