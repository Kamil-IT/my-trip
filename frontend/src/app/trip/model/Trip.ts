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
