export * from './authenticationController.service';
import { AuthenticationControllerService } from './authenticationController.service';
export * from './medicoController.service';
import { MedicoControllerService } from './medicoController.service';
export * from './prenotazioneController.service';
import { PrenotazioneControllerService } from './prenotazioneController.service';
export * from './userController.service';
import { UserControllerService } from './userController.service';
export const APIS = [AuthenticationControllerService, MedicoControllerService, PrenotazioneControllerService, UserControllerService];
