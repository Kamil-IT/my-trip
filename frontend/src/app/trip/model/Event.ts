interface LocationDescription {
  locationDescription: string;
}

export interface Event {
  location: LocationDescription;
  eventType: string;
  to: string;
  from: string;
  title: string;
}
