import { Component } from '@angular/core';
import { Category, CategoryService } from '../../../../../data';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent {

  categories: Category[] = [];
  constructor(private categoryService: CategoryService){}

  ngOnInit(): void {
    this.categoryService.getAll().subscribe(data => {
       this.categories = data
    });
  }

}
