import {Component, Input, OnInit} from '@angular/core';
import {Property} from "../../../model/Event";

@Component({
  selector: 'app-event-weather-table',
  templateUrl: './event-weather-table.component.html',
  styleUrls: ['./event-weather-table.component.scss']
})
export class EventWeatherTableComponent implements OnInit {
  @Input()
  properties: Property[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
