import { Component } from '@angular/core';
import { Category, CategoryService } from '../../../../../data';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent {

  subcription: Subscription = new Subscription;
  categories: Category[] = [];
  constructor(private categoryService: CategoryService){}

  ngOnInit(): void {
    this.subcription = this.categoryService.getAll().subscribe(data => {
       this.categories = data
    });
  }
  ngOnDestroy(): void {
    this.subcription.unsubscribe();
  }
}
