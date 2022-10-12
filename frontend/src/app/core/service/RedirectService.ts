import {Injectable} from "@angular/core";
import {AuthService} from "./AuthService";
import {HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";


@Injectable()
export class RedirectService {

  constructor(private readonly router: Router) {}

  redirectHome(): Promise<boolean> {
    return this.router.navigate(['/'])
  }

  redirectLogin(): Promise<boolean> {
    return this.router.navigate(['/'])
  }
}
