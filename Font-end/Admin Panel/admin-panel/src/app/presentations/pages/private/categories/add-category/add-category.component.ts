import { Component } from '@angular/core';
import { Category, CategoryService } from '../../../../../data';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent {

  

  constructor(private categoryService: CategoryService, private router: Router){}

  addCategory(name: string, type: string){
    console.log(`name: ${name} and type: ${type}`)
    let category: Category = {
      name: name,
      type: type
    }
    this.categoryService.addCategory(category).subscribe({
      next: (v) =>{ 
        console.log(v);
        this.router.navigate(['/admin/category'])
       },
      error: (er) => { console.log(er); },
      complete: () => console.log('Add Category Complete')
    })
  }

  cancel(){
    console.log('Cancel')
    this.router.navigate(['/admin/category'])
  }

}
