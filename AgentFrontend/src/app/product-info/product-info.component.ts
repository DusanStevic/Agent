import {Component, OnInit} from '@angular/core';
import {Product} from '../model/Product';
import {ProductService} from '../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styleUrls: ['./product-info.component.scss']
})
export class ProductInfoComponent implements OnInit {


  constructor(private productService: ProductService, private activeRoute: ActivatedRoute,
              private authenticationService: AuthenticationService,
              private router: Router) {
  }

  product: Product;
  id: string;

  ngOnInit(): void {
    this.id = this.activeRoute.snapshot.paramMap.get('id');
    this.getProduct();
  }

  getProduct() {
    this.productService.getProduct(this.id).subscribe(
      response => {
        this.product = response;
      }
    );
  }

  loggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }

  deleteProduct() {
    this.productService.deleteProduct(this.id).subscribe(
      response => {
        this.router.navigateByUrl('');
      }
    );
  editProduct() {
    this.router.navigateByUrl('edit-product/' + this.id);
  }

}
