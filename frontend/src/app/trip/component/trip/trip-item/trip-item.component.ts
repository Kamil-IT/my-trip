import {Component, Input, OnInit} from '@angular/core';
import {Trip} from "../../../model/Trip";

@Component({
  selector: 'app-trip-item',
  templateUrl: './trip-item.component.html',
  styleUrls: ['./trip-item.component.scss']
})
export class TripItemComponent implements OnInit {

  @Input()
  trip: Trip | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
