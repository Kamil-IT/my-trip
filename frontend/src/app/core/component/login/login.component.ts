import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/AuthService";
import {Router} from "@angular/router";
import {RedirectService} from "../../service/RedirectService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(private readonly authService: AuthService,
              private readonly redirectService: RedirectService) {
    if (authService.isUserAuthenticated()) {
      redirectService.redirectHome();
    }
  }

  ngOnInit(): void {
  }

  login() {
    this.authService.authorizeUser(this.email, this.password)
      .subscribe(() => this.redirectService.redirectHome());
  }

  createAccount() {
    this.authService.createUser(this.email, this.password)
      .subscribe(() => this.redirectService.redirectHome());
  }
}
