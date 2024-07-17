import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { Medico, MedicoControllerService } from '../../../api/v1';

@Component({
  selector: 'app-medici-get',
  standalone: true,
  imports: [HttpClientModule, MatTableModule],
  templateUrl: './medici-get.component.html',
  styleUrl: './medici-get.component.css'
})
export class MediciGetComponent implements OnInit {

  listaMedici: Array<Medico> = [];

  displayedColumns: string[] = ['indirizzoStudio', 'nome', 'prezzoVisita', 'specialita'];

  constructor(private medicoService: MedicoControllerService) {}

  ngOnInit(): void {
    
    // richiamo la funzione del per recuperare la lista
    console.log('richiamo metodo service findAll');

    this.medicoService.findAll1().subscribe(data => {
      //console.log(`data ${data}`);
      console.log('reagito ad evento del service');
      this.listaMedici = data as Array<Medico>;
      console.log(this.listaMedici);


  });

}
}