import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Product} from '../model/Product';
import {ProductService} from '../service/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private productService: ProductService) {
  }

  products: Array<Product> = [];

  ngOnInit(): void {
    this.getProducts();
  }

  newProduct() {
    this.router.navigateByUrl('new-product');
  }

  getProducts() {
    this.productService.getProducts().subscribe(
      response => {
        this.products = response;
      }
    );
  }

  reports() {
    this.router.navigateByUrl('report');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

}
