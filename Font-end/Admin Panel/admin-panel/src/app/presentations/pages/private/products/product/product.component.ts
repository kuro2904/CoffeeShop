import { Component } from '@angular/core';
import { Product, ProductService } from '../../../../../data';
import { Subscription } from 'rxjs';
import { ENV } from '../../../../../config/EnvironmentConfig';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

  subcription: Subscription = new Subscription;
  products: Product[] = [];
  constructor(private productService: ProductService){}

  ngOnInit(): void {
    this.subcription = this.productService.getAll().subscribe({
      next: (req) => {
        req.forEach(i =>{
         let imagesUrl: string[] = []
         i.images.forEach(i => {
          imagesUrl.push(`http://${ENV.API_HOST}:8080/api/v1/images/${i}`)
         })
          let product:Product ={
            id: i.id,
            name: i.name,
            categoryId:i.categoryId,
            categoryName: i.categoryName,
            description: i.description,
            isActive:i.isActive,
            details: i.details,
            images: imagesUrl
          }
          this.products.push(product)
        }
      );
        
      },
      error: (err) => console.log(err),
      complete: () => console.log("Load product complete")
    })
  }

  ngOnDestroy(): void {
    this.subcription.unsubscribe();
  }
}
