import { Component } from '@angular/core';
import { AuthService } from '../Services/authService/auth.service';


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

  constructor(private authService: AuthService) { }

  register(): void {
    this.authService.register(this.user)
      .subscribe(
        {
          next: () => alert('Registration sucessful!'),
          error: (error) => alert('Registraion failed.' + error.message)
        });
  }

}
