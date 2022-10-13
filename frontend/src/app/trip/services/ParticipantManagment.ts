import {AddParticipantModel} from "../model/Trip";
import {Observable} from "rxjs";

export interface ParticipantManagment {
  addParticipant(addParticipantModel: AddParticipantModel): void;
  removeParticipant(addParticipantModel: AddParticipantModel): void;
  getAllParticipantsEmail(parentId: string): Observable<string[]>;
}
