package it.jac.spring.secondaprova.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.jac.spring.secondaprova.entity.FasciaOraria;
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
	    
	    public ResponseEntity<String> addPrenotazione(LocalDate giorno, String fasciaOraria, Medico idMedico) {
	    	
	    	log.info("richiamato metodo intervento service addPrenotazione");
		    	
		    String codConferma = UUID.randomUUID().toString(); 
		    
		    Prenotazione prenotazione = new Prenotazione (giorno, fasciaOraria, codConferma, idMedico);
		    	
		    log.info("avvio procedimento inserimento prenotazione");
		    	
		    repository.save(prenotazione);
		    	
		    return ResponseEntity.ok("OK, added");
	    	
	    }
	    
	    // FIND BY GIORNO PRENOTAZIONE
	    
	    public List<Prenotazione> findByGiorno(LocalDate giorno) {
	    	
	    	log.info("richiamato metodo service findByGiorno");
	    	
	    	return repository.findByGiorno(giorno);
	    	
	    }
}
