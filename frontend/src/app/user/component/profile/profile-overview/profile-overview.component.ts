import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../service/UserService";
import {Observable} from "rxjs";
import {User} from "../../../model/User";
import {AuthService} from "../../../../core/service/AuthService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile-overview',
  templateUrl: './profile-overview.component.html',
  styleUrls: ['./profile-overview.component.scss']
})
export class ProfileOverviewComponent implements OnInit {

  user :Observable<User>;

  constructor(private userService: UserService,
              private readonly authService: AuthService,
              private readonly router: Router) {
    this.authService.tryLogin(() => {}, () => this.router.navigate(['/login']));
    this.user = this.userService.getCurrentUser();
  }

  ngOnInit(): void {
  }

}
