package it.jac.spring.secondaprova.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.jac.spring.secondaprova.entity.Medico;
import it.jac.spring.secondaprova.service.MedicoService;

@RestController
@RequestMapping(path = "/api/v1/medico", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicoController {
	
	private static Logger log = LogManager.getLogger(MedicoController.class);
	
	MedicoService service;
	  
	  	@Autowired
	    public MedicoController(MedicoService service) {
	        this.service = service;
	    }
	  
	  	// GET MEDICI
	  	
	    @GetMapping("/get")
	    public List<Medico> findAll() {
			log.info("richiamato metodo GET findAll");
	        return service.findAll();
	    }
	    
	    // ADD MEDICO
	    
		@PostMapping("/add")
	    public ResponseEntity<String> addMedico(@RequestParam String nome, 
	                                             @RequestParam String indirizzoStudio,
	                                             @RequestParam String specialita,
	                                             @RequestParam Double prezzoVisita) {
	        
			log.info("richiamato metodo POST addMedico");
	        ResponseEntity<String> response = service.addMedico(nome, indirizzoStudio, specialita, prezzoVisita);
	        
	        return response;
	    }
	
	    
	    
	    
}
