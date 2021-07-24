import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/Product';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private cloudinary = 'https://api.cloudinary.com/v1_1/detmeun2w/image/upload';
  private cloudinaryUploadPreset = 'wur6lgyd';


  constructor(private http: HttpClient) {
  }

  createProduct(dto: Product, file: File, success, failure) {
    console.log(file);

    if (!file) {
      console.log(`${environment.agentApi}agent-product-service/api/product`);
      this.http.post<Product>(`${environment.agentApi}agent-product-service/api/product`, dto).subscribe(
        response => {
          success();
        }, error => {
          failure();
        }
      );
      return;
    }

    this.uploadImage(file).subscribe(
      response => {
        dto.imagePath = response.url;
        this.http.post<Product>(`${environment.agentApi}agent-product-service/api/product`, dto).subscribe(
          serverResponse => {
            success();
          }, serverError => {
            failure();
          }
        );
      }
    );

  }

  uploadImage(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', this.cloudinaryUploadPreset);
    formData.append('cloud_name', 'detmeun2w');
    return this.http.post(this.cloudinary, formData);
  }
}
