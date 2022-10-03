import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TripService} from "../../../services/TripService";
import {Observable} from "rxjs";
import {Trip} from "../../../model/Trip";

@Component({
  selector: 'app-trip-edit',
  templateUrl: './trip-edit.component.html',
  styleUrls: ['./trip-edit.component.scss']
})
export class TripEditComponent implements OnInit {

  private readonly tripId;
  tripResponse$: Observable<Trip>;

  constructor(private route: ActivatedRoute,
              private readonly tripService: TripService,
              private cdRef: ChangeDetectorRef) {
    this.tripId = this.route.snapshot.params['id']

    this.tripResponse$ = tripService.getTripById(this.tripId);
    this.tripResponse$.subscribe(trp => console.log(trp))
  }

  ngOnInit(): void {
  }

}
