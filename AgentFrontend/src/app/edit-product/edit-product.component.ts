import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductService} from '../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Product} from '../model/Product';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {

  productForm: FormGroup;
  selectedFile: File;
  url: any;
  id: string;
  product: Product;

  constructor(private formBuilder: FormBuilder,
              private productService: ProductService,
              private router: Router,
              private snackBar: MatSnackBar,
              private activeRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.id = this.activeRoute.snapshot.paramMap.get('id');
    this.productService.getProduct(this.id).subscribe(
      response => {
        this.product = response;
        this.productForm.controls.productName.setValue(this.product.name);
        this.productForm.controls.productPrice.setValue(this.product.price);
        this.productForm.controls.productAvailability.setValue(this.product.available);
        this.url = this.product.imagePath;
      }
    );
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

  editProduct() {
    this.productService.editProduct(
      new Product(Number(this.id),
        this.productName,
        this.productPrice,
        this.productAvailability,
        this.url),
      this.selectedFile,
      this.editProductSuccess.bind(this),
      this.editProductFailure.bind(this));
  }

  editProductSuccess() {
    this.router.navigateByUrl('');
  }

  editProductFailure() {
    this.snackBar.open('Error creating product');
  }

  back() {
    this.router.navigateByUrl('product/' + this.id);
  }
}
