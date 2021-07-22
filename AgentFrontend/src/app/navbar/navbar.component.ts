import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  getLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }

  logIn() {
    this.router.navigateByUrl('login');
  }

  logOut() {
    localStorage.clear();
  }

}
