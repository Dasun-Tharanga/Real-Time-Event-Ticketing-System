import { Component } from '@angular/core';
import { AuthService } from '../Services/authService/auth.service';
import { Router } from '@angular/router';
import { LogService } from '../Services/log/log.service';

@Component({
  selector: 'app-sign-in',
  standalone: false,

  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {

  credentials = { username: '', password: '' };

  constructor(private authService: AuthService, private router: Router, private logService: LogService) { }

  login(): void {
    this.authService.login(this.credentials.username, this.credentials.password).subscribe({
      next: (response: any) => {
        if (response.status === 200) {
          alert(response.body);
          this.logService.addLog("logged in");
          this.router.navigate(['/customer']);

        }
      },
      error: (error) => {
        if (error.status === 401) {
          alert('Login failed: Invalid username or password.');
        } else {
          alert('Login failed: ' + (error.error || 'Unknown error'));
        }
      }
    });
  }
}
