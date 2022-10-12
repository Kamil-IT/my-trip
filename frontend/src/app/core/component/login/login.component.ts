import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/AuthService";
import {Router} from "@angular/router";
import {CurrentUserPrivilegesService} from "../../service/CurrentUserPrivilegesService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(private readonly authService: AuthService,
              private readonly router: Router,
              private readonly currentUser: CurrentUserPrivilegesService) {
    authService.tryLogin(() => {}, () => this.router.navigate(['/login']));
  }

  ngOnInit(): void {
  }

  login() {
    this.authService.authorizeUser(this.email, this.password)
      .subscribe(() => this.redirectToHome());
  }

  createAccount() {
    this.authService.createUser(this.email, this.password)
      .subscribe(() => this.redirectToHome());
  }

  createAdminAccount() {
    this.authService.createAdminUser(this.email, this.password)
      .subscribe(() => this.redirectToHome());
  }

  private redirectToHome() {
    this.router.navigate(['/'])
    this.currentUser.refreshUser()
  }
}
