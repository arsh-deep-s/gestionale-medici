package it.jac.spring.secondaprova.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.jac.spring.secondaprova.entity.FasciaOraria;
import it.jac.spring.secondaprova.entity.Medico;
import it.jac.spring.secondaprova.entity.Prenotazione;
import it.jac.spring.secondaprova.service.PrenotazioneService;

@RestController
@RequestMapping(path = "/api/v1/prenotazioni", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrenotazioneController {
	
	private static Logger log = LogManager.getLogger(PrenotazioneController.class);
	
	PrenotazioneService service;
	
    @Autowired
    public PrenotazioneController(PrenotazioneService service) {
        this.service = service;
    }
	
    // GET PRENOTAZIONI
    
	@GetMapping("/get")
	public List<Prenotazione> findAll() {
		log.info("richiamato metodo GET intervento findAll");
		return service.findAll();
	}
	
	// ADD PRENOTAZIONE
	
	@PostMapping("/add")
    public ResponseEntity<String> addPrenotazione(@RequestParam LocalDate giorno,
    											@RequestParam String fasciaOraria,
                                                @RequestBody Medico idMedico) {
        
		log.info("richiamato metodo POST addPrenotazione");	
		ResponseEntity<String> response = service.addPrenotazione(giorno, fasciaOraria, idMedico);
        
        return response;
    }
	
  	// FIND BY DATE PRENOTAZIONE
  	
    @GetMapping("/findbydate")
    public List<Prenotazione> findByDate(@RequestParam LocalDate giorno) {
    	
		log.info("richiamato metodo GET findByDate");
        return service.findByGiorno(giorno);
    }
}
