import { HttpClient } from '@angular/common/http';
import { Injectable, Input } from '@angular/core';
import { Observable } from 'rxjs';
import {ENV} from '../../config/EnvironmentConfig'
import { Token } from '../models/token';
import { LocalStorageService } from './local-storage.service';
import {jwtDecode} from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient,private storageService: LocalStorageService) {}

  login(userName: string, password: string): Observable<Token> {
    return this.http.post<Token>(
      `http://${ENV.API_HOST}:8080/api/v1/auth/login`,
      { email: userName, password: password }
    );
  }

  isAuthenticated(): boolean {
    const token: string = this.storageService.getItem('token') ?? null;
    if(token == null || token.length == 0 || !this.getValidRole(token)) return false;
    return true;
  }

  getValidRole(jwt: string): boolean {
    if (jwt == null) return false;
    const decodedToken: any = jwtDecode(jwt);
    const roles: string[] = decodedToken.role.map((role: any) => role.role);
    const hasRoleEmployee = roles.includes('ROLE_EMPLOYEE');
    if (hasRoleEmployee) return true;
    return false;
  }

  logout(): void{
    this.storageService.removeData('token');
  }
}
