import { Component } from '@angular/core';
import { AuthService } from '../Services/authService/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-sign-up',
  standalone: false,

  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {

  user = {
    name: '',
    username: '',
    email: '',
    password: ''

  }

  constructor(private authService: AuthService, private router: Router) { }

  register(): void {
    this.authService.register(this.user).subscribe({
      next: (response: any) => {
        // Check if the HTTP status code is 200 for successful registration
        if (response.status === 200) {
          alert(response.body);
          // Display success message from the backend
          this.router.navigate([''])
        }
      },
      error: (error) => {
        // Handle error scenarios, such as username already taken or other issues
        if (error.status === 400) {
          alert('Registration failed: ' + error.error); // Display the error message from the backend
        } else {
          alert('Registration failed: ' + (error.error || 'Unknown error occurred.'));
        }
      }
    });
  }

}
