import {Component, Input, OnInit} from '@angular/core';
import {TripService} from "../../../services/TripService";
import {FormBuilder} from "@angular/forms";
import {AddParticipantModel} from "../../../model/Trip";
import {ParticipantManagment} from "../../../services/ParticipantManagment";
import {Observable} from "rxjs";

@Component({
  selector: 'app-trip-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.scss']
})
export class ParticipantComponent implements OnInit {

  participantForm = this.formBuilder.group({});

  cache = 0;

  participantEmails :Observable<string[]> | undefined;

  @Input()
  parentId: string = '';

  @Input()
  participantManagement: ParticipantManagment = {
    getAllParticipantsEmail(parentId: string): Observable<string[]> { throw new Error('not implemented')},
    removeParticipant(addParticipantModel: AddParticipantModel): void {},
    addParticipant(addParticipantModel: AddParticipantModel): void {}
  };

  @Input()
  participantEmail: string = '';

  @Input()
  isExisingParticipant: boolean = false;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  save(): void {
    let addParticipantModel = {
      email: this.participantEmail,
      uuid: this.parentId
    };
    this.participantManagement.addParticipant(addParticipantModel)
  }

  remove() {
    let addParticipantModel = {
      email: this.participantEmail,
      uuid: this.parentId
    };
    this.participantManagement.removeParticipant(addParticipantModel);
  }

  getAllParticipants() : Observable<string[]> {
    console.log(this.participantEmail)
    // Add user service and delete users with already exist in event/trip + current
    return this.getAllParticipantsFromCache();
  }

  private getAllParticipantsFromCache() {
    if (this.participantEmails){
      return this.participantEmails;
    }
    this.participantEmails = this.getAllParticipantsFromServer();
    return this.participantEmails;
  }

  private getAllParticipantsFromServer() {
    return this.participantManagement.getAllParticipantsEmail(this.parentId);
  }
}
