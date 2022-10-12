import {Injectable} from "@angular/core";
import {AuthService} from "./AuthService";
import {HttpHeaders} from "@angular/common/http";


@Injectable()
export class HeaderService {

  constructor(private readonly authService: AuthService) {}

  getHeaders(): HttpHeaders {
    return new HttpHeaders({
      authorization : this.authService.getUserDetailsHeaderAuth(),
      'Content-Type':  'application/json',
    });
  }
}
