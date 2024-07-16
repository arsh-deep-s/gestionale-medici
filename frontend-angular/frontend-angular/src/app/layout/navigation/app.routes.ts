import { Routes } from '@angular/router';
import { MediciGetComponent } from '../../components/medici/medici-get/medici-get.component';
import { PrenotazioniGetComponent } from '../../components/prenotazioni/prenotazioni-get/prenotazioni-get.component';
import { PrenotazioniAddComponent } from '../../components/prenotazioni/prenotazioni-add/prenotazioni-add.component';

export const routes: Routes = [
    {path: '', pathMatch: 'full', redirectTo: '/index'},
    {path: 'listamedici', component: MediciGetComponent},
    {path: 'listaprenotazioni', component: PrenotazioniGetComponent},
    {path: 'inserisciprenotazione', component: PrenotazioniAddComponent},

];
