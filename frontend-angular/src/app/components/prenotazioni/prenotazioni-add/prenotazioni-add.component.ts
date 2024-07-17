import { DatePipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { RouterLink, Router } from '@angular/router';
import { PrenotazioneControllerService, MedicoControllerService, Medico, Prenotazione } from '../../../api/v1';
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
    private router: Router,
    private datePipe: DatePipe) { }

    listaPrenotazioniFiltroMediciGiorni: Array<Prenotazione> = [];
    listaMedici: Array<Medico> = [];
    listaFascieOrarie: Array<string> = [
      '9:00 - 10:00',
      '10:00 - 11:00',
      '11:00 - 12:00',
      '12:00 - 13:00',
      '13:00 - 14:00',
      '14:00 - 15:00',
      '15:00 - 16:00',
      '16:00 - 17:00',
      '17:00 - 18:00',
      '18:00 - 19:00'
    ];

  private fb = inject(FormBuilder);

  prenotazioneForm = this.fb.group({
    // imposto le stesse maxlenght che ho impostato nel database, in modo da avere il controllo
    // sia sul frontend che sul backend
    fasciaOraria: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
    giorno: [null, Validators.compose([Validators.required, Validators.maxLength(12)])],
    medico: [null, Validators.compose([Validators.required])],
  });

  ngOnInit(): void {

    // RECUPERO MEDICI
    //console.log('richiamo metodo service findAll');
    this.medicoService.findAll1().subscribe(data => {

      console.log('reagito ad evento del service medici');
      //console.log(`data ${data}`);
      this.listaMedici = data as Array<Medico>;
      console.log(this.listaMedici);

    });
    console.log('termine chiamata funzione service medici');
}

onSubmit(): void {

  const giorno: Date = this.prenotazioneForm.value.giorno ?? new Date();

  const formattedGiorno = this.datePipe.transform(giorno, 'yyyy-MM-dd') ?? 'error';

  const fasciaOraria: string = this.prenotazioneForm.value.fasciaOraria ?? '';

  const medico: Medico | null = this.prenotazioneForm.value.medico ?? null;

  console.log('richiamo metodo service insertData');

  if (medico) {
    this.prenotazioneService.addPrenotazione(
      formattedGiorno,
      fasciaOraria,
      medico
    ).subscribe(data => {
  
      console.log('reagito ad evento del service');
      console.log(`data ${data}`);
  
    });
  
    console.log('termine chiamata funzione service');
    this.router.navigate(['/listaprenotazioni'])
  }

}

getFasceOrarieOccupate(){

  const giorno: Date = this.prenotazioneForm.value.giorno ?? new Date();

  const formattedGiorno = this.datePipe.transform(giorno, 'yyyy-MM-dd') ?? 'error';

  const medico: Medico | null = this.prenotazioneForm.value.medico ?? null;

  console.log(medico);

  if (medico) {
    this.prenotazioneService.findByDateAndMedico(
      formattedGiorno, 
      medico).subscribe(data => {
  
        console.log('reagito ad evento findByDateAndMedico');
      //console.log(`data ${data}`);
      this.listaPrenotazioniFiltroMediciGiorni = data as Array<Medico>;
      console.log(this.listaPrenotazioniFiltroMediciGiorni);

      // Estrai le fasce orarie in una lista di stringhe e filtra i valori undefined
      const fasceOrarie: string[] = this.listaPrenotazioniFiltroMediciGiorni
        .map(item => item.fasciaOraria)
        .filter((fasciaOraria): fasciaOraria is string => fasciaOraria !== undefined);

        console.log('FASCE ORARIE ESTRATTE', fasceOrarie);
  
    });
  }
  
}

}