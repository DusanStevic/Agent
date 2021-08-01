import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginRequest} from '../model/LoginRequest';
import {Observable} from 'rxjs';
import {LoginResponse} from '../model/LoginResponse';
import {environment} from '../../environments/environment';
import {PurchaseRequest} from '../model/PurchaseRequest';
import {PurchaseResponse} from '../model/PurchaseResponse';
import {Report} from '../model/Report';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http: HttpClient) {
  }

  buy(buyRequest: PurchaseRequest): Observable<PurchaseResponse> {
    return this.http.post<PurchaseResponse>(`${environment.agentApi}agent-shop-service/api/shop`, buyRequest);
  }

  report(): Observable<Report> {
    return this.http.get<Report>(`${environment.agentApi}agent-shop-service/api/shop/report`);
  }
}
