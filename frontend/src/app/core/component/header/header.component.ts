import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../service/AuthService";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router,
              private readonly authService: AuthService) {
  }

  ngOnInit(): void {
  }

  toHome(): void {
    this.router.navigate(['/'])
  }

  toProfile(): void {
    this.router.navigate(['/user'])
  }

  toUsers(): void {
    this.router.navigate(['/user'])
  }

  logout(): void {
    this.authService.logout(() => this.router.navigate(['/login']));
  }

}
