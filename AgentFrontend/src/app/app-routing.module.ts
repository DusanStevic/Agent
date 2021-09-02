import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {NewProductComponent} from './new-product/new-product.component';
import {ProductInfoComponent} from './product-info/product-info.component';
import {EditProductComponent} from './edit-product/edit-product.component';
import {BuyProductComponent} from './buy-product/buy-product.component';
import {SuccessPurchaseComponent} from './success-purchase/success-purchase.component';
import {ReportComponent} from './report/report.component';



const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'new-product', component: NewProductComponent},
  {path: 'product/:id', component: ProductInfoComponent},
  {path: 'edit-product/:id', component: EditProductComponent},
  {path: 'buy/:id', component: BuyProductComponent},
  {path: 'success', component: SuccessPurchaseComponent},
  {path: 'report', component: ReportComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
