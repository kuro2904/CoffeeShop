import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }

  public saveData(key: string, data: string): void {
    localStorage.setItem(key,data)
  }

  public removeData(key: string): void {
    localStorage.removeItem(key)
  }

  public getItem(key: string): string{
     return localStorage.getItem(key)?? '';
  }

  public clearData(): void {
    localStorage.clear;
  }
}
