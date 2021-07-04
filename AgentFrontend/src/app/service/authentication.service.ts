import {Injectable} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {LoginRequest} from '../model/LoginRequest';
import {Observable} from 'rxjs';
import {LoginResponse} from '../model/LoginResponse';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {
  }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.agentApi}agent-shop-service/auth/login`, loginRequest);
  }
}
