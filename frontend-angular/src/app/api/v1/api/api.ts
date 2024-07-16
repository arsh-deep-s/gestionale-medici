export * from './clienteController.service';
import { ClienteControllerService } from './clienteController.service';
export * from './fasciaOrariaController.service';
import { FasciaOrariaControllerService } from './fasciaOrariaController.service';
export * from './medicoController.service';
import { MedicoControllerService } from './medicoController.service';
export * from './prenotazioneController.service';
import { PrenotazioneControllerService } from './prenotazioneController.service';
export const APIS = [ClienteControllerService, FasciaOrariaControllerService, MedicoControllerService, PrenotazioneControllerService];
