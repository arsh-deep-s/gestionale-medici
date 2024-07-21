package it.jac.spring.secondaprova.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                                                @RequestBody Medico Medico) {
        
		log.info("richiamato metodo POST addPrenotazione");	
		ResponseEntity<String> response = service.addPrenotazione(giorno, fasciaOraria, Medico);
        
        return response;
    }
	
	// DELETE PRENOTAZIONE
	
	@PostMapping("/delete")
    public ResponseEntity<Map<String,String>> deletePrenotazione(@RequestParam Long id) {
        
		log.info("richiamato metodo POST deletePrenotazione");	
		ResponseEntity<Map<String,String>> response = service.deletePrenotazioneById(id);
        
        return response;
    }
	
	// FIND PRENOTAZIONE BY ID  - USED TO EDIT DATA
	
//	@GetMapping("/findbyid")
//    public Optional<Prenotazione> findPrenotazioneById(@RequestParam Long id) {
//        
//        log.info("Richiamato metodo GET findPrenotazioneById");
//        
//        Optional<Prenotazione> response = service.findById(id);
//        
//        return response;
//    }
	
 
  	// FILTER BY GIORNO E MEDICO
  	
    @PostMapping("/findbygiornoandmedico")
    public List<Prenotazione> findByDateAndMedico(@RequestParam LocalDate giorno,
    												@RequestBody Medico medico) {
    	
    	
    	// TEST REPO
//    	Medico medico = new Medico();
//    	
//    	medico.setId(1L);
//    	medico.setNome("Arsh");
//    	medico.setIndirizzoStudio("Castello");
//    	medico.setSpecialita("Occulista");
//    	medico.setPrezzoVisita(191.00);
    	
		log.info("richiamato metodo GET findByDateAndMedico");
        return service.findByGiornoAndMedico(giorno, medico);
    }
}
