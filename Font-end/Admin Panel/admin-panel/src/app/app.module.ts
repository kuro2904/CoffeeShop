import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { provideHttpClient, withJsonpSupport } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './presentations/pages/private/home/home.component';
import { ProductComponent } from './presentations/pages/private/products/product/product.component';
import { OrderComponent } from './presentations/pages/private/order/order.component';
import { LoginComponent } from './presentations/pages/auth/login/login.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIcon } from '@angular/material/icon';
import { MatButton } from '@angular/material/button';
import { LayoutComponent } from './presentations/layout/layout.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import { AddProductComponent } from './presentations/pages/private/products/add-product/add-product.component';
import { ProductLayoutComponent } from './presentations/pages/private/products/product-layout/product-layout.component';
import { AddCategoryComponent } from './presentations/pages/private/categories/add-category/add-category.component';
import { CategoryLayoutComponent } from './presentations/pages/private/categories/category-layout/category-layout.component';
import { CategoryComponent } from './presentations/pages/private/categories/category/category.component';
import { MatOption, MatSelect } from '@angular/material/select';
import { FormGroup } from '@angular/forms';


@NgModule({
  declarations: [AppComponent, HomeComponent, ProductComponent, CategoryComponent, OrderComponent, LoginComponent, LayoutComponent, AddProductComponent, ProductLayoutComponent, AddCategoryComponent, CategoryLayoutComponent],
  imports: [BrowserModule, AppRoutingModule, MatFormFieldModule, MatInputModule,MatIcon,MatButton,MatDialogModule,MatTableModule,MatIconModule, MatToolbarModule,MatButtonModule,MatDividerModule,MatToolbarModule,MatSelect,MatOption],
  providers: [provideAnimationsAsync(), provideHttpClient(withJsonpSupport())],
  bootstrap: [AppComponent],
})
export class AppModule {}
