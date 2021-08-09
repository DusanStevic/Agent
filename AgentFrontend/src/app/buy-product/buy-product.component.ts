import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductService} from '../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../model/Product';
import {ShopService} from '../service/shop.service';
import {PurchaseRequest} from '../model/PurchaseRequest';

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.scss']
})
export class BuyProductComponent implements OnInit {

  buyForm: FormGroup;
  url: any;
  id: string;
  product: Product;

  constructor(private formBuilder: FormBuilder,
              private productService: ProductService,
              private shopService: ShopService,
              private router: Router,
              private snackBar: MatSnackBar,
              private activeRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.id = this.activeRoute.snapshot.paramMap.get('id');
    this.productService.getProduct(this.id).subscribe(
      response => {
        this.product = response;
      }
    );
    this.buyForm = this.formBuilder.group({
      quantity: ['', [
        Validators.required,
      ]],
      name: ['', [
        Validators.required,
      ]],
      address: ['', [
        Validators.required,
      ]],
      phone: ['', [
        Validators.required,
      ]]
    });
  }

  get name() {
    return this.buyForm.controls.name.value as string;
  }

  get address() {
    return this.buyForm.controls.address.value as string;
  }

  get phone() {
    return this.buyForm.controls.phone.value as string;
  }

  get quantity() {
    return this.buyForm.controls.quantity.value as number;
  }

  back() {
    this.router.navigateByUrl('');
  }

  buy() {
    const buyRequest = new PurchaseRequest(Number(this.id), this.quantity, this.name, this.phone, this.address);
    this.shopService.buy(buyRequest).subscribe(
      response => {
        this.router.navigateByUrl('success');
      },
      error => {
        this.snackBar.open('Error buying');
      }
    );
  }

}
