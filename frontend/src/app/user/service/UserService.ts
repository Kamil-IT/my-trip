import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../model/User";

@Injectable()
export class TripService {

  private readonly BASE_URL = 'http://localhost:8080/v1/user';

  private readonly tripsSubject = new BehaviorSubject<User>({  email: '', password: ''});

  constructor(private readonly http: HttpClient) {}

  getUsers(): Observable<User> {
    this.populateNewUsers()
    return this.tripsSubject.asObservable();
  }

  getUserById(tripId: string): Observable<User> {
    return this.http.get<User>(this.BASE_URL + '/' + tripId);
  }

  populateNewUsers(): void {
    this.http.get<User>(this.BASE_URL).subscribe(res => {
      this.tripsSubject.next(res);
    })
  }

}
