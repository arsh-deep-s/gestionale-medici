import { Component, inject } from '@angular/core';
import { AuthService } from '../../../auth/service/auth.service';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    ReactiveFormsModule,
    RouterLink,
    MatDatepickerModule,
    FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  private fb = inject(FormBuilder);

  loginForm = this.fb.group({
    // imposto le stesse maxlenght che ho impostato nel database, in modo da avere il controllo
    // sia sul frontend che sul backend
    email: [null, Validators.compose([Validators.required, Validators.maxLength(30), Validators.email])],
    password: [null, Validators.compose([Validators.required, Validators.maxLength(30)])],
  });

  onSubmit(): void {

    const email: string = this.loginForm.value.email ?? '';
    const password: string = this.loginForm.value.password ?? '';

    console.log('richiamo metodo login');

    this.authService.login(email, password).subscribe({
      next: (response: any) => {
        console.log('Login successful');
        this.authService.saveToken(response.token); // salvo il token
        console.log(response.token);
        // Esegui la redirect verso /listaprenotazioni se il login è riuscito
        
      },
      error: (error: HttpErrorResponse) => {
        console.error('Login failed:', error);
        // Gestisci errori di login (username non trovato, password errata)
        // Mostra messaggio all'utente o gestisci l'errore come preferisci
        // Esempio di gestione errori semplice:
        if (error.status === 401) {
          alert('Username o password errati.');
        } else {
          alert('Errore durante il login. Si prega di riprovare più tardi.');
        }
      },
      complete: () => {
        console.log('Operazione di login completata');
      }
    });

  }
}
