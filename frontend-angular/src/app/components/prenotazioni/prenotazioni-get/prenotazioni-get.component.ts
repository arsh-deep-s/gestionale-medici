import { Component, OnInit } from '@angular/core';
import { Prenotazione, PrenotazioneControllerService } from '../../../api/v1';
import { HttpClientModule, HttpErrorResponse, HttpResponse, HttpStatusCode } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-prenotazioni-get',
  standalone: true,
  imports: [HttpClientModule, MatTableModule, RouterLink],
  templateUrl: './prenotazioni-get.component.html',
  styleUrl: './prenotazioni-get.component.css'
})
export class PrenotazioniGetComponent implements OnInit {

  listaPrenotazioni: Array<Prenotazione> = [];

  displayedColumns: string[] = ['id', 'codConferma', 'fasciaOraria', 'giorno', 'medico', 'delete'];

  constructor(private prenotazioneService: PrenotazioneControllerService) { }

  ngOnInit(): void {

    // richiamo la funzione del per recuperare la lista
    console.log('richiamo metodo service findAll');

    this.prenotazioneService.findAll().subscribe(data => {
      console.log(`data ${data}`);
      console.log('reagito ad evento del service');
      this.listaPrenotazioni = data as Array<Prenotazione>;
      console.log(this.listaPrenotazioni);
    });

  }

  // FUNZIONE PER ELIMINARE PRENOTAZIONI, STAMPA MESSAGGI DI ERRORE & OK
  deletePrenotazione(id: any) {
    this.prenotazioneService.deletePrenotazione(id).subscribe({
      next: (response: any) => {
        console.log('reagito ad evento del service elimina prenotazione');
        console.log(response.message);
        alert(response.message); // Mostra il messaggio di successo all'interno di un alert
      },
      error: (error: HttpErrorResponse) => {
        console.error(JSON.stringify(error));
        alert(error.error.message); // Mostra l'errore all'interno di un alert
      },
      complete: () => {
        console.log('Operazione completata');
      }
    });

    alert('Funzione lanciata!');
  }
}