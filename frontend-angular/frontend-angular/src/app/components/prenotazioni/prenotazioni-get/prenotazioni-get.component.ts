import { Component, OnInit } from '@angular/core';
import { Prenotazione, PrenotazioneControllerService } from '../../../api/v1';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-prenotazioni-get',
  standalone: true,
  imports: [HttpClientModule, MatTableModule],
  templateUrl: './prenotazioni-get.component.html',
  styleUrl: './prenotazioni-get.component.css'
})
export class PrenotazioniGetComponent implements OnInit{

  listaPrenotazioni: Array<Prenotazione> = [];

  displayedColumns: string[] = ['id', 'codConferma', 'fasciaOraria', 'giorno', 'idMedico'];

  constructor(private prenotazioneService: PrenotazioneControllerService) {}

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
}