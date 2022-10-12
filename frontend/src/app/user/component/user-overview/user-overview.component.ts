import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../service/UserService";
import {User} from "../../model/User";
import {Observable} from "rxjs";
import {AuthService} from "../../../core/service/AuthService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-overview',
  templateUrl: './user-overview.component.html',
  styleUrls: ['./user-overview.component.scss']
})
export class UserOverviewComponent implements OnInit {

  users : Observable<User[]>;

  constructor(private readonly userService: UserService,
              private readonly authService: AuthService,
              private readonly router: Router) {
    this.authService.tryLogin(() => {}, () => this.router.navigate(['/login']));

    this.users = userService.getUsers();
    this.users.subscribe((res) => console.log(res))
  }

  ngOnInit(): void {
  }

}
