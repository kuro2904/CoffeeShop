import { Component } from '@angular/core';
import { Product, ProductService } from '../../../../../data';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

  products: Product[] = [];
  constructor(private productService: ProductService){}

  ngOnInit(): void {
    this.productService.getAll().subscribe({
      next: (req) => {
        this.products = req;
        console.log(this.products);
      },
      error: (err) => console.log(err),
      complete: () => console.log("Load product complete")
    })
  }

}
