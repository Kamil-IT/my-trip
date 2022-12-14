import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TripService} from "../../../services/TripService";
import {Observable} from "rxjs";
import {Trip} from "../../../model/Trip";
import {CurrentUserPrivilegesService} from "../../../../core/service/CurrentUserPrivilegesService";
import {AuthService} from "../../../../core/service/AuthService";

@Component({
  selector: 'app-trip-edit',
  templateUrl: './trip-edit.component.html',
  styleUrls: ['./trip-edit.component.scss']
})
export class TripEditComponent implements OnInit {

  private readonly tripId;
  tripResponse$: Observable<Trip>;

  title: string = '';
  from: string = '';
  to: string = '';

  constructor(private route: ActivatedRoute,
              readonly tripService: TripService,
              readonly router: Router,
              readonly currentUser: CurrentUserPrivilegesService,
              readonly authService: AuthService) {
    this.authService.tryLogin(() => {}, () => this.router.navigate(['/login']));
    this.tripId = this.route.snapshot.params['id']

    this.tripResponse$ = tripService.getTripById(this.tripId);
    this.tripResponse$.subscribe(trp => {
      this.title = trp.title;
      this.from = trp.from;
      this.to = trp.to;
      console.log(trp)
    })
  }

  ngOnInit(): void {
  }

  save() {
    this.tripService.updateTrip({uuid: this.tripId, title: this.title, from: this.from, to: this.to})
      .subscribe(trp => console.log(trp))
  }

  remove() {
    this.tripService.deleteTrip(this.tripId)
      .subscribe(() => this.router.navigate(['/']));
  }
}
