import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './presentations/pages/auth/login/login.component';
import { AuthGuardService } from './data/services/auth-guard.service';
import { LayoutComponent } from './presentations/layout/layout.component';
import { HomeComponent } from './presentations/pages/private/home/home.component';
import { ProductComponent } from './presentations/pages/private/products/product/product.component';
import { OrderComponent } from './presentations/pages/private/order/order.component';
import { AddProductComponent } from './presentations/pages/private/products/add-product/add-product.component';
import { AddCategoryComponent } from './presentations/pages/private/categories/add-category/add-category.component';
import { CategoryLayoutComponent } from './presentations/pages/private/categories/category-layout/category-layout.component';
import { CategoryComponent } from './presentations/pages/private/categories/category/category.component';


const routes: Routes = [
  {
    path:'',
    redirectTo:'login',
    pathMatch:'full',
  },
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'admin',
    component:LayoutComponent,
    canActivate:[AuthGuardService],
    children:[
      {
        'path':'',
        redirectTo:'home',
        pathMatch:'full'
      },
      {
        path:'home',
        component:HomeComponent,

      },
      {
        path:'product',
        children:[
          {
            path:'',
            component:ProductComponent
          },
          {
            path:'add',
            component:AddProductComponent,
          }
        ]
      },
      {
        path:'category',
        component:CategoryLayoutComponent,
        children:[
          {
            path:'',
            component:CategoryComponent,
          },
          {
            path:'add',
            component:AddCategoryComponent
          }
        ]
      },
      {
        path:'order',
        component:OrderComponent,
      }
    ]
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
