import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductService} from '../service/product.service';
import {Product} from '../model/Product';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.scss']
})
export class NewProductComponent implements OnInit {
  productForm: FormGroup;
  selectedFile: File;
  url: any;

  constructor(private formBuilder: FormBuilder,
              private productService: ProductService,
              private router: Router,
              private snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      productName: ['', [
        Validators.required,
      ]],
      productPrice: ['', [
        Validators.required,
      ]],
      productAvailability: ['', [
        Validators.required,
      ]]
    });
  }

  get productName() {
    return this.productForm.controls.productName.value as string;
  }

  get productPrice() {
    return this.productForm.controls.productPrice.value as number;
  }

  get productAvailability() {
    return this.productForm.controls.productAvailability.value as number;
  }


  onFileChanged(event) {
    if (event.target.files && event.target.files[0]) {
      this.selectedFile = event.target.files[0];
      const reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (newEvent) => { // called once readAsDataURL is completed
        this.url = newEvent.target.result;
      };
    }
  }

  createProduct() {
    this.productService.createProduct(
      new Product(null,
        this.productName,
        this.productPrice,
        this.productAvailability,
        null),
      this.selectedFile,
      this.createProductSuccess.bind(this),
      this.createProductFailure.bind(this));
  }

  createProductSuccess() {
    this.router.navigateByUrl('');
  }

  createProductFailure() {
    this.snackBar.open('Error creating product');
  }
  back() {
    this.router.navigateByUrl('');
  }
}
