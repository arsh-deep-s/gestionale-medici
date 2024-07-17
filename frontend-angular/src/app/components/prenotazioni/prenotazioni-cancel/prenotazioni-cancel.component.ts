import { DatePipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PrenotazioneControllerService, MedicoControllerService, Prenotazione, Medico } from '../../../api/v1';

@Component({
  selector: 'app-prenotazioni-cancel',
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
  templateUrl: './prenotazioni-cancel.component.html',
  styleUrl: './prenotazioni-cancel.component.css'
})
export class PrenotazioniCancelComponent {

  id: any;
  prenotazione: any;

  constructor(private prenotazioneService: PrenotazioneControllerService,
    private medicoService: MedicoControllerService,
    private router: Router,
    private datePipe: DatePipe,
    private activatedRoute: ActivatedRoute) { }

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

    // GET PRENOTAZIONE ID /cancellaprenotazione/{{id}}
    this.activatedRoute.params.subscribe(params => {
      this.id = params?.['id'];
      console.log('id: ', this.id);
    });

    // RECUPERO OGGETTO PRENOTAZIONE
    this.prenotazioneService.findPrenotazioneById(this.id).subscribe(data => {

      console.log('reagito ad evento del service recupero prenotazione by id');
      //console.log(`data ${data}`);
      this.prenotazione = data as Prenotazione;
      console.log(this.prenotazione);

      // Popolare il form con i dati recuperati
      this.prenotazioneForm.patchValue({
        giorno: this.prenotazione.giorno,
        fasciaOraria: this.prenotazione.fasciaOraria,
        medico: this.prenotazione.medico
      });

    });

    // RECUPERO LISTA MEDICI
    //console.log('richiamo metodo service findAll');
    this.medicoService.findAll1().subscribe(data => {

      console.log('reagito ad evento del recupero medici');
      //console.log(`data ${data}`);
      this.listaMedici = data as Array<Medico>;
      //console.log(this.listaMedici);

    });

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
      ).subscribe(Response => {

        console.log('reagito ad evento del service');

      });

      console.log('termine chiamata funzione service');
      this.router.navigate(['/listaprenotazioni'])
    }

    console.log('PRENOTAZIONE:', this.prenotazione);
  };

}

