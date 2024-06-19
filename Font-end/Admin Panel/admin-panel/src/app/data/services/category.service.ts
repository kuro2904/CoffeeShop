import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { ENV } from '../../config/EnvironmentConfig';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient, private storageService: LocalStorageService) { }

  getAll(): Observable<Category[]>{
    return this.http.get<Category[]>(`http://${ENV.API_HOST}:8080/api/v1/categories`);
  }

  addCategory(category: Category): Observable<Category> {
    const token = this.storageService.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.post<Category>(
      `http://${ENV.API_HOST}:8080/api/v1/categories`,
      JSON.stringify(category),
      { headers }
    );
  }
}
