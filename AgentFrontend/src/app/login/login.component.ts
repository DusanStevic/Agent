import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {LoginRequest} from '../model/LoginRequest';
import {AuthenticationService} from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide = true;


  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService,
              private snackBar: MatSnackBar, private router: Router) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', [
        Validators.required
      ]],
      password: ['', [
        Validators.required,
      ]]
    });
  }

  get username() {
    return this.loginForm.controls.username.value as string;
  }

  get password() {
    return this.loginForm.controls.password.value as string;
  }

  onLogInSubmit() {
    const loginData = new LoginRequest(this.username, this.password);
    this.authenticationService.login(loginData).subscribe(
      (response => {
        if (response != null) {
          localStorage.setItem('token', response.token);
          this.snackBar.open('Logged In successfully.');
          this.router.navigateByUrl('');
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      }));
  }

}
