import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/service/auth.service';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [MatToolbarModule,
    MatButtonModule,
    MatCardModule],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent {

  constructor(private authService: AuthService, private router: Router) { }

  logout(): void {
    
    // elimino il token ed effettuo il logout
    console.log('logout...');

    this.authService.logout();
    this.router.navigate(['/login']);

  };
}
