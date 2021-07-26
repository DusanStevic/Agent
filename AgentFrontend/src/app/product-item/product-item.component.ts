import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../model/Product';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss']
})
export class ProductItemComponent implements OnInit {

  constructor(private router: Router) {
  }

  @Input() product: Product;

  ngOnInit(): void {
  }

  viewProduct(id: number) {
    this.router.navigateByUrl('product/' + id);
  }

}
