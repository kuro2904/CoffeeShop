import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ENV } from '../../config/EnvironmentConfig';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  uploadImages(images: File[]): Observable<string[]> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.localStorageService.getItem('token')}`
    });

    const fd = new FormData();
    images.forEach((file: File) => {
      fd.append('files', file);
    });

    return this.http.post<string[]>(`http://${ENV.API_HOST}:8080/api/v1/images`, fd, { headers });
  }
}
