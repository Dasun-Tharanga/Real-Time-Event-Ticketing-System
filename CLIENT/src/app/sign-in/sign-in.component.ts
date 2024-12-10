import { Component } from '@angular/core';
import { AuthService } from '../Services/authService/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  standalone: false,

  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {

  credentials = { username: '', password: '' };

  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        alert(response);
        this.router.navigate(['/customer'])
      },
      error: (error) => alert('Login failed.' + error.message)
    });
  }
}
