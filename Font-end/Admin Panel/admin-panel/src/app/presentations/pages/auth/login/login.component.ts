import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, LocalStorageService } from '../../../../data';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  hide = true;
  clickEvent(event: MouseEvent){
    this.hide = !this.hide;
    event.stopPropagation();
  }

  constructor(private authService: AuthService, private storageService:LocalStorageService, private router: Router){}

  ngOnInit(): void {
    if(this.authService.isAuthenticated()) this.router.navigate(['/admin/home'])
  }

  login(email: string, password: string): void {
      this.authService.login(email, password).subscribe({
      next: (token) => {
        this.storageService.saveData('token',token.token);
        this.router.navigate(['/admin/home'], { replaceUrl: true });
        console.log(this.storageService.getItem('token'))
      },
      error: (err) => {console.log(err)},
      complete: () => console.log('Complete')
    });
  }
}
