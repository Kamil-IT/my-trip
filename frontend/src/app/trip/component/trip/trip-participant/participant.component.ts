import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AddParticipantModel} from "../../../model/Trip";
import {ParticipantManagment} from "../../../services/ParticipantManagment";
import {map, Observable} from "rxjs";
import {UserService} from "../../../../user/service/UserService";
import {User} from "../../../../user/model/User";

@Component({
  selector: 'app-trip-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.scss']
})
export class ParticipantComponent implements OnInit {

  participantForm = this.formBuilder.group({});

  cache = 0;

  participantEmails: Observable<string[]> | undefined;

  @Input()
  parentId: string = '';

  @Input()
  participantManagement: ParticipantManagment = {
    getAllParticipantsEmail(parentId: string): Observable<string[]> {
      throw new Error('not implemented')
    },
    removeParticipant(addParticipantModel: AddParticipantModel): void {
    },
    addParticipant(addParticipantModel: AddParticipantModel): void {
    }
  };

  @Input()
  participantEmail: string = '';

  @Input()
  isExisingParticipant: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private readonly userService: UserService) {
  }

  ngOnInit(): void {
  }

  save(): void {
    console.log(this.participantEmail)
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

  getAllParticipants(): Observable<string[]> {
    return this.getAllParticipantsFromCache()
      .pipe(map((usersEmail: string[]) => ['', ...usersEmail]));
  }

  private getAllParticipantsFromCache() {
    let email = this.participantEmail;
    console.log(email)
    if (this.participantEmails) {
      return this.participantEmails;
    }
    let participantEmail = this.getAllParticipantsFromServer();
    this.participantEmails = participantEmail;
    participantEmail.subscribe(() => this.participantEmail = email);
    return this.participantEmails;
  }

  private getAllParticipantsFromServer() : Observable<string[]> {
    return this.userService.getUsers()
      .pipe(map((users: User[]) => users.map(user => user.email)));
  }
}
