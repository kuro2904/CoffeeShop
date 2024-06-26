import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ENV } from '../../config/EnvironmentConfig';
import { Product } from '../models/product';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  getAll():Observable<Product[]>{
    return this.http.get<Product[]>(`http://${ENV.API_HOST}:8080/api/v1/products`);
  }

  insertProduct(product: Product){
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.localStorageService.getItem('token')}`
    });
    return this.http.post<Product>(`http://${ENV.API_HOST}:8080/api/v1/products`,product, {headers} );
  }
}
