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

import it.jac.spring.secondaprova.entity.Cliente;
import it.jac.spring.secondaprova.service.ClienteService;

@RestController
@RequestMapping(path = "/api/v1/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
	
	private static Logger log = LogManager.getLogger(ClienteController.class);
	
	ClienteService service;
	  
	  	@Autowired
	    public ClienteController(ClienteService service) {
	        this.service = service;
	    }
	  
	  	// GET CLIENTI
	  	
	    @GetMapping("/get")
	    public List<Cliente> findAll() {
			log.info("richiamato metodo GET findAll");
	        return service.findAll();
	    }
	    
	    // ADD CLIENTE
	    
		@PostMapping("/register")
	    public ResponseEntity<String> addCliente(@RequestParam String nome, 
	                                             @RequestParam String cognome,
	                                             @RequestParam String mail,
	                                             @RequestParam String password,
	                                             @RequestParam String codiceFiscale) {
	        
			log.info("richiamato metodo POST addCliente");
	        ResponseEntity<String> response = service.addCliente(nome, cognome, mail, password, codiceFiscale);
	        
	        return response;
	    }
	
		
	  	// LOGIN CLIENTE
	  	
	    @PostMapping("/login")
	    public ResponseEntity<String> loginCliente(@RequestParam String username,
	                                               @RequestParam String password) {
	    	
			log.info("richiamato metodo POST loginCliente");
	        return service.login(username, password);
	    }
	    
	   	    
}
