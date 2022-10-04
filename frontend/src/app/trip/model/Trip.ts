import {Event} from "./Event";

export interface Trip {
  uuid: string;
  title: string;
  creatorEmail: string;
  from: string;
  to: string;
  participantsEmails: string[];
  events: Event[];
}

export interface TripsResponse {
  trips: Trip[];
}

export interface CreateTripRequestModel {
  title: string | null;
  from: string | null;
  to: string | null;
}

export interface UpdateTripRequestModel {
  uuid: string | null;
  title: string | null;
  from: string | null;
  to: string | null;
}

export interface AddParticipantModel {
  email: string | null;
  uuid: string | null;
}
