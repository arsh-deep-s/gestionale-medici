import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { Router, RouterLink } from '@angular/router';
import { AuthenticationControllerService } from '../../../api/v1';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    ReactiveFormsModule,
    RouterLink,
    MatDatepickerModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  constructor(private authenticationControllerService: AuthenticationControllerService,
    private router: Router) { }

    private fb = inject(FormBuilder);


    signUpForm = this.fb.group({
      // imposto le stesse maxlenght che ho impostato nel database, in modo da avere il controllo
      // sia sul frontend che sul backend
      nome: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
      cognome: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
      email: [null, Validators.compose([Validators.required, Validators.email, Validators.maxLength(30)])],
      password: [null, Validators.compose([Validators.required, Validators.maxLength(30)])],
      codiceFiscale: [null, Validators.compose([Validators.required, Validators.maxLength(16)])]
    });
  
    onSubmit(): void {
  
      const nome: string = this.signUpForm.value.nome ?? '';
      const cognome: string = this.signUpForm.value.cognome ?? '';
      const email: string = this.signUpForm.value.email ?? '';
      const password: string = this.signUpForm.value.password ?? '';
      const codiceFiscale: string = this.signUpForm.value.codiceFiscale ?? '';
  
    
      console.log('richiamo metodo service register');
    
      this.authenticationControllerService.register({email, password, nome, cognome, codiceFiscale})
      .subscribe(Response => {
    
        console.log('reagito ad evento del service');
    
      });
    
      console.log('termine chiamata funzione service');
  
      // DOPO LA REGISTRAZIONE FACCIO LA REDIRECT SUL LOGIN
      
      this.router.navigate(['/login'])
    
    }

}
