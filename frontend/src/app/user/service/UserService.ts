import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../model/User";
import {HeaderService} from "../../core/service/HeaderService";

@Injectable()
export class UserService {

  private readonly BASE_URL = 'http://localhost:8080/v1/user';

  private readonly tripsSubject = new BehaviorSubject<User[]>([{email: '', password: '', authorities: [{authority: ''}]}]);

  constructor(private readonly http: HttpClient,
              private readonly headerService: HeaderService) {
  }

  getUsers(): Observable<User[]> {
    this.populateNewUsers()
    return this.tripsSubject.asObservable();
  }

  saveUser(email: string, password: string): void {
    this.http.post<User>(this.BASE_URL, {email: email, password: password}).subscribe(res => {
      this.populateNewUsers()
    });
  }

  populateNewUsers(): void {
    this.http.get<User[]>(this.BASE_URL, {headers: this.headerService.getHeaders()}).subscribe(res => {
      this.tripsSubject.next(res);
    })
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(this.BASE_URL + '/current', {headers: this.headerService.getHeaders()});
  }
}
