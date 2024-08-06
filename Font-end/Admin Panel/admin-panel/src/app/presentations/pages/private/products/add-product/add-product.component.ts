import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import {
  Category,
  CategoryService,
  Product,
  ProductDetail,
  ProductService,
} from '../../../../../data';
import { ImageService } from '../../../../../data/services/image.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css',
})
export class AddProductComponent {
  categories: Category[] = []; // Array to hold categories
  selectedImages: string[] = []; // Array to hold selected image previews
  compressedImages: File[] = []; // Array to hold compressed images
  productDetails: ProductDetail[] = []; // Array to hold product details
  productForm: FormGroup;
  constructor(
    private categoryService: CategoryService,
    private imageService: ImageService,
    private productService: ProductService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      categoryId: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.categoryService.getAll().subscribe((categories) => {
      this.categories = categories;
    });
  }

  onFileSelected(event: any) {
    const files: FileList = event.target.files;
    if (files) {
      for (let i = 0; i < files.length; i++) {
        console.log(files[i]);
        this.compressImage(files[i]).then((compressedImage) => {
          console.log(compressedImage);
          this.compressedImages.push(compressedImage); // Add compressed image to array

          const reader = new FileReader();
          reader.onload = (e: any) => {
            this.selectedImages.push(e.target.result);
            // Add image preview
          };
          reader.readAsDataURL(compressedImage);
        });
      }
    }
  }

  compressImage(file: File): Promise<File> {
    const fileSizeMax = 1 * 1024 * 1024; // 1MB
    const widthHeightMax = 1024; // 1024px
    const defaultWidthHeightRatio = 1;
    const defaultQualityRatio = 0.7;

    return new Promise((resolve, reject) => {
      const imageType = file.type || 'image/jpeg';
      const reader = new FileReader();

      reader.readAsDataURL(file);
      reader.onload = (event) => {
        const img = new Image();
        img.src = (event.target as FileReader).result as string;

        img.onload = () => {
          const imgWH = img.width > img.height ? img.width : img.height;
          const widthHeightRatio =
            imgWH > widthHeightMax
              ? widthHeightMax / imgWH
              : defaultWidthHeightRatio;
          const qualityRatio =
            file.size > fileSizeMax
              ? fileSizeMax / file.size
              : defaultQualityRatio;

          const canvas = document.createElement('canvas');
          canvas.width = img.width * widthHeightRatio;
          canvas.height = img.height * widthHeightRatio;

          const ctx = canvas.getContext('2d');
          ctx?.drawImage(img, 0, 0, canvas.width, canvas.height);
          canvas.toBlob(
            (blob) => {
              if (blob) {
                resolve(
                  new File([blob], file.name, {
                    type: imageType,
                    lastModified: Date.now(),
                  })
                );
              } else {
                reject(new Error('Image compression failed'));
              }
            },
            imageType,
            qualityRatio
          );
        };

        img.onerror = () => {
          reject(new Error('Image load failed'));
        };
      };

      reader.onerror = () => {
        reject(new Error('File reading failed'));
      };
    });
  }

  addDetails(size: string, price: string) {
    this.productDetails.push({ size, price: Number(price) });
  }

  removeDetail(index: number) {
    this.productDetails.splice(index, 1);
  }

  uploadProduct(name: string, description: string, categoryId: number) {
    // Prepare the form data for upload

    console.log(this.productForm.get('categoryId')?.value);
    const formData = new FormData();

    this.compressedImages.forEach((image, index) => {
      formData.append(`images[${index}]`, image);
    });

    let imageSubcription: Subscription;
    imageSubcription = this.imageService
      .uploadImages(this.compressedImages)
      .subscribe({
        next: (v) => {
          console.log(v);

          let product: Product = {
            name: name,
            description: description,
            isActive: 1,
            rate: 5,
            rateSum: 0,
            categoryId: categoryId,
            details: this.productDetails,
            images: v,
          };
          console.log(product);

          let productSubcription: Subscription;
          productSubcription = this.productService
            .insertProduct(product)
            .subscribe({
              next: () => {
                this.router.navigateByUrl('admin/product');
              },
              error: (er) => {
                console.log(er);
              },
              complete: () => {
                console.log('Add Product Successful');
                productSubcription.unsubscribe();
              },
            });
        },
        error: (er) => console.log(er),
        complete: () => {
          console.log('Upload images complete');
          imageSubcription.unsubscribe();
        },
      });
  }
}
