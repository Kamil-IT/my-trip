import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import {TripService} from "../../../services/TripService";
import {CreateTripRequestModel} from "../../../model/Trip";
import {CurrentUserPrivilegesService} from "../../../../core/service/CurrentUserPrivilegesService";

@Component({
  selector: 'app-trip-new',
  templateUrl: './trip-new.component.html',
  styleUrls: ['./trip-new.component.scss']
})
export class TripNewComponent implements OnInit {

  tripForm = this.formBuilder.group({
    title: '',
    from: '',
    to: ''
  });

  constructor(private readonly tripService: TripService,
              private formBuilder: FormBuilder,
              readonly currentUser: CurrentUserPrivilegesService) { }

  ngOnInit(): void {
  }

  onSubmit(requestModel: CreateTripRequestModel) {
    this.tripService.createTrip(requestModel);
  }
}
