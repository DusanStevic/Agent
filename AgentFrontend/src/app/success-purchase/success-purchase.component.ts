import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-success-purchase',
  templateUrl: './success-purchase.component.html',
  styleUrls: ['./success-purchase.component.scss']
})
export class SuccessPurchaseComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  back() {
    this.router.navigateByUrl('');
  }

}
