import { Component } from '@angular/core';
import { AuthService } from '../../Services/authService/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-sing-in',
  standalone: false,

  templateUrl: './admin-sing-in.component.html',
  styleUrl: './admin-sing-in.component.css'
})
export class AdminSingInComponent {

  credentials = { username: '', password: '' };

  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    this.authService.adminLogin(this.credentials.username, this.credentials.password).subscribe({
      next: (response) => {
        //alert(response);
        if (response) { this.router.navigate(['/vendor']) }
      },
      error: (error) => alert('Login failed.' + error.message)
    });
  }

}
