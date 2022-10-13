import {Injectable} from "@angular/core";
import {AuthService} from "./AuthService";
import {HttpHeaders} from "@angular/common/http";
import {UserService} from "../../user/service/UserService";


@Injectable()
export class CurrentUserPrivilegesService {

  authority = 'USER';

  constructor(private readonly userService: UserService) {
    this.refreshUser();
  }

  isAdmin(): boolean {
    return this.authority === 'ADMIN';
  }

  isUser(): boolean {
    return this.authority === 'USER';
  }

  refreshUser(): void {
    this.userService.getCurrentUser().subscribe((res) => {
      let authorityReal = res.authorities.find(() => true)?.authority;
      this.authority = authorityReal === undefined ? 'ADMIN' : authorityReal;
    });
  }
}
