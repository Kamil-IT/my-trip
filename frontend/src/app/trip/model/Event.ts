export interface Event {
  creatorEmail: string;
  uuid: string;
  location: LocationDescription;
  eventType: string;
  to: string;
  from: string;
  title: string;
  properties: Property[];
  participants: string[];
}

export interface LocationDescription {
  locationDescription: string;
}

export interface Property {
  key: string;
  value: string;
}

export interface CreateEvent {
  tripId: string;
  title: string;
  locationDescription: string;
  from: string;
  to: string;
  type: string;
  properties: Map<string, string>[];
}

export interface UpdateEvent {
  locationDescription: string;
  from: string;
  to: string;
  title: string;
}
