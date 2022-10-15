import {Event} from "./Event";

export interface Address {
  streetAddress: string;
  locality: string;
}

export interface Coordinate {
  lat: number;
  lon: number;
}

export interface OptimizedThumbUrls {
  srpDesktop: string;
}

export interface Hotel {
  id: number;
  name: string;
  starRating: string;
  address: Address;
  coordinate: Coordinate;
  optimizedThumbUrls: OptimizedThumbUrls;
}

export interface SearchResults {
  results: Hotel[];
}

export interface HotelsResponse {
  searchResults: SearchResults;
}


export interface AddAccommodationDto {
  photoUrl: string;
  hotelName: string;
  hotelRating: string;
  address: string;
  eventUuid: string;
}
