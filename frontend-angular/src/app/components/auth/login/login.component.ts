import { Component } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string = 'ciao@gmail.com';
  password: string = 'mysql';

  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    
    this.authService.login(this.email, this.password).subscribe(response => {
      this.authService.saveToken(response.token);
      console.log(response.token);
      //this.router.navigate(['/']);
    });
  }

}
