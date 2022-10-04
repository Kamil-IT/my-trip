import {Component, Input, OnInit} from '@angular/core';
import {TripService} from "../../../services/TripService";
import {FormBuilder, ɵElement, ɵFormGroupRawValue, ɵTypedOrUntyped} from "@angular/forms";
import {AddParticipantModel} from "../../../model/Trip";

@Component({
  selector: 'app-trip-participant',
  templateUrl: './trip-participant.component.html',
  styleUrls: ['./trip-participant.component.scss']
})
export class TripParticipantComponent implements OnInit {

  participantForm = this.formBuilder.group({});

  @Input()
  tripId: string = '';

  @Input()
  participantEmail: string = '';

  @Input()
  isExisingParticipant: boolean = false;

  constructor(private readonly tripService: TripService,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  save(): void {
    let addParticipantModel = {
      email: this.participantEmail,
      uuid: this.tripId
    };
    console.log(addParticipantModel)
    this.tripService.addParticipant(addParticipantModel)
      .subscribe(res => console.log(res))
  }

  remove() {
    let addParticipantModel = {
      email: this.participantEmail,
      uuid: this.tripId
    };
    console.log(addParticipantModel)
    this.tripService.removeParticipant(addParticipantModel)
      .subscribe(res => console.log(res))
  }
}
