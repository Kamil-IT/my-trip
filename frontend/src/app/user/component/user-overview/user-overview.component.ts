import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../service/UserService";
import {User} from "../../model/User";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user-overview',
  templateUrl: './user-overview.component.html',
  styleUrls: ['./user-overview.component.scss']
})
export class UserOverviewComponent implements OnInit {

  users : Observable<User[]>;

  constructor(private readonly userService: UserService) {
    this.users = userService.getUsers();
    this.users.subscribe((res) => console.log(res))
  }

  ngOnInit(): void {
  }

}
