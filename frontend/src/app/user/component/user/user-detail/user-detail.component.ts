import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../../service/UserService";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {

  @Input()
  email = '';
  @Input()
  password = '';

  constructor(private readonly userService: UserService) { }

  ngOnInit(): void {
  }

  save() {
    this.userService.saveUser(this.email, this.password);
    this.email = '';
    this.password = '';
  }
}
