import {Component, Input, OnInit} from '@angular/core';
import {Trip} from "../../../model/Trip";
import {Router} from "@angular/router";

@Component({
  selector: 'app-trip-item',
  templateUrl: './trip-item.component.html',
  styleUrls: ['./trip-item.component.scss']
})
export class TripItemComponent implements OnInit {

  @Input()
  trip: Trip | undefined;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  redirectToTripEdit(uuid: string | undefined) {
    this.router.navigate(['/trip/' + uuid])
  }
}
