import { DatePipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { RouterLink, Router } from '@angular/router';
import { PrenotazioneControllerService, MedicoControllerService, Medico, FasciaOraria, FasciaOrariaControllerService } from '../../../api/v1';
import {MatRadioModule} from '@angular/material/radio';
import {MatInputModule} from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';

@Component({
  selector: 'app-prenotazioni-add',
  standalone: true,
  providers: [provideNativeDateAdapter(), DatePipe],
  imports: [MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    ReactiveFormsModule,
    RouterLink,
    MatDatepickerModule],
  templateUrl: './prenotazioni-add.component.html',
  styleUrl: './prenotazioni-add.component.css'
})
export class PrenotazioniAddComponent {

  constructor(private prenotazioneService: PrenotazioneControllerService,
    private medicoService: MedicoControllerService,
    private fasciaOrariaService: FasciaOrariaControllerService,
    private router: Router,
    private datePipe: DatePipe) { }

    listaMedici: Array<Medico> = [];
    listaFascieOrarie: Array<FasciaOraria> = [];

  private fb = inject(FormBuilder);

  prenotazioneForm = this.fb.group({
    // imposto le stesse maxlenght che ho impostato nel database, in modo da avere il controllo
    // sia sul frontend che sul backend
    fascia_oraria: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
    giorno: [null, Validators.compose([Validators.required, Validators.maxLength(12)])],
    id_medico: [null, Validators.compose([Validators.required])],
  });

  ngOnInit(): void {

    // RECUPERO TIPOLOGIE
    //console.log('richiamo metodo service findAll');

    this.medicoService.findAll1().subscribe(data => {

      console.log('reagito ad evento del service medici');
      //console.log(`data ${data}`);
      this.listaMedici = data as Array<Medico>;
      console.log(this.listaMedici);

    });
    console.log('termine chiamata funzione service medici');

        // RECUPERO FASCIE ORARIE
    //console.log('richiamo metodo service findAll');

    this.fasciaOrariaService.findAll2().subscribe(data => {

      console.log('reagito ad evento del service medici');
      //console.log(`data ${data}`);
      this.listaFascieOrarie = data as Array<FasciaOraria>;
      console.log(this.listaFascieOrarie);

    });
    console.log('termine chiamata funzione service medici');

}

onSubmit(): void {

  const fascia_oraria: string = this.prenotazioneForm.value.fascia_oraria ?? '';

  const giorno: Date = this.prenotazioneForm.value.giorno ?? new Date();

  const formattedGiorno = this.datePipe.transform(giorno, 'yyyy-MM-dd') ?? 'error';

  const id_medico: Medico | null = this.prenotazioneForm.value.id_medico ?? null;

  console.log('richiamo metodo service insertData');

  console.log('MEDICO:',id_medico)
  console.log('ORARIA:',fascia_oraria)

  if (id_medico) {
    this.prenotazioneService.addPrenotazione(
      formattedGiorno,
      fascia_oraria,
      id_medico
    ).subscribe(Response => {
  
      console.log('reagito ad evento del service');
  
    });
  
    console.log('termine chiamata funzione service');
    this.router.navigate(['/listaprenotazioni'])
  }

}

}