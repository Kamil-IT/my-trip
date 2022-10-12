import {Injectable} from "@angular/core";
import {User} from "../../user/model/User";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class AuthService {

  private readonly CREATE_USER_URL = 'http://localhost:8080/v1/user';
  private readonly CHECK_IS_USER_AUTHENTICATED_URL = 'http://localhost:8080/v1/trip';

  private email: string = '';
  private password: string = '';

  constructor(private readonly http: HttpClient) {
  }

  getUserDetailsHeaderAuth(): string {
    return 'Basic ' + btoa(this.email + ':' + this.password);
  }

  authorizeUser(email: string, password: string): Observable<void> {
    this.email = email;
    this.password = password;
    return this.tryToLogIn();
  }

  isUserAuthenticated(): boolean {
    return this.email != '' && this.password != '';
  }

  createUser(email: string, password: string): Observable<User> {
    let userObservable = this.http.post<User>(
      this.CREATE_USER_URL,
      {
        email: email,
        password: password
      });

    userObservable
      .subscribe(() => {
        this.email = email;
        this.password = password;
      });

    return userObservable;
  }

  private tryToLogIn(): Observable<void> {
    let observable = this.http.get<void>(this.CHECK_IS_USER_AUTHENTICATED_URL, {
      headers: new HttpHeaders({
        authorization: this.getUserDetailsHeaderAuth(),
        'Content-Type': 'application/json',
      })
    });
    observable
      .subscribe(() => {
      }, () => {
        this.email = '';
        this.email = '';
      });

    return observable;
  }
}
