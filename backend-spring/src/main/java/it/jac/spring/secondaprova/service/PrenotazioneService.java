package it.jac.spring.secondaprova.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.jac.spring.secondaprova.entity.Medico;
import it.jac.spring.secondaprova.entity.Prenotazione;
import it.jac.spring.secondaprova.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	
	private static Logger log = LogManager.getLogger(PrenotazioneService.class);
    
    private PrenotazioneRepository repository;

    @Autowired
    public PrenotazioneService(PrenotazioneRepository repository) {
        this.repository = repository;
    
    }

    	// GET PRENOTAZIONI
    
	    public List<Prenotazione> findAll() {
	    	log.info("richiamato metodo intervento service findAll");
	        return (List<Prenotazione>) repository.findAll();
	    }
	    
	    // ADD PRENOTAZIONE
	    
	    public ResponseEntity<String> addPrenotazione(LocalDate giorno, String fasciaOraria, Medico Medico) {
	    	
	    	log.info("richiamato metodo intervento service addPrenotazione");
		    	
		    String codConferma = UUID.randomUUID().toString();
		    
		    Prenotazione prenotazione = new Prenotazione (giorno, fasciaOraria, codConferma, Medico);
		    	
		    log.info("avvio procedimento inserimento prenotazione");
		    	
		    repository.save(prenotazione);
		    	
		    return ResponseEntity.ok("OK, added");
	    	
	    }
	    
	    // DELETE PRENOTAZIONE BY ID
	    
	    public ResponseEntity<String> deletePrenotazioneById(Long id) {
	    	
	    	log.info("richiamato metodo intervento service deletePrenotazioneById");
	    	
	    	// CONTROLLO VERICIA 48 ORE
	    	
	    	Optional<Prenotazione> optionalPrenotazione = repository.findById(id);
	    	
	    	if (optionalPrenotazione.isPresent()) {
	            Prenotazione prenotazione = optionalPrenotazione.get();
	            
	            // Ottiene la data della prenotazione
	            LocalDate dataPrenotazione = prenotazione.getGiorno();
	            
	            // Crea un LocalDateTime per la data della prenotazione (inizio del giorno)
	            LocalDateTime dateTimePrenotazione = LocalDateTime.of(dataPrenotazione, LocalTime.MIN);
	            
	            // Ottiene l'ora attuale
	            LocalDateTime now = LocalDateTime.now();
	            
	            // Calcola la data e ora limite per eliminare la prenotazione (48 ore prima della prenotazione)
	            LocalDateTime limiteEliminazione = dateTimePrenotazione.minusHours(48);
	            
	            // Verifica se è ancora possibile eliminare la prenotazione
	            if (now.isBefore(limiteEliminazione)) {
	                // Elimina la prenotazione dal repository
	                repository.deleteById(id);
	                return ResponseEntity.ok("OK, deleted");
	            } else {
	                // Se non è possibile eliminare la prenotazione perché mancano meno di 48 ore
	                return ResponseEntity.badRequest().body("Impossibile eliminare la prenotazione, mancano meno di 48 ore!");
	            }
	        } else {
	            // Se la prenotazione non è stata trovata nel repository
	            return ResponseEntity.badRequest().body("Not found.");
	        }
	    	
	    }
	    
	    
	    // FIND PRENOTAZIONE BY ID
	    
	    public Optional<Prenotazione> findById(Long id) {
	    	
	    	log.info("richiamato metodo service findById");
	    	
	    	return repository.findById(id);
	    	
	    }
	    
	    // FIND BY GIORNO PRENOTAZIONE
	    
	    public List<Prenotazione> findByGiorno(LocalDate giorno) {
	    	
	    	log.info("richiamato metodo service findByGiorno");
	    	
	    	return repository.findByGiorno(giorno);
	    	
	    }
	    
	    // FIND BY GIORNO AND MEDICO PRENOTAZIONE
	    
	    public List<Prenotazione> findByGiornoAndMedico(LocalDate giorno, Medico medico) {
	    	
	    	log.info("richiamato metodo service findByGiornoAndMedico");
	    	
	    	return repository.findByGiornoAndMedico(giorno, medico);
	    	
	    }
}
