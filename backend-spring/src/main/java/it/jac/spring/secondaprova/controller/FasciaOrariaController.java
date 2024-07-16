package it.jac.spring.secondaprova.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.spring.secondaprova.entity.FasciaOraria;
import it.jac.spring.secondaprova.service.FasciaOrariaService;

@RequestMapping(path = "/api/v1/fasciaoraria", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class FasciaOrariaController {

	private static Logger log = LogManager.getLogger(FasciaOrariaController.class);
	
	FasciaOrariaService service;
	
    @Autowired
    public FasciaOrariaController(FasciaOrariaService service) {
        this.service = service;
    }
    
	@GetMapping("/get")
	public List<FasciaOraria> findAll() {
		log.info("richiamato metodo GET FasciaOraria findAll");
		return service.findAll();
	}
	
//	@PostMapping("/add")
//    public ResponseEntity<String> addMedico(@RequestParam String nome, 
//                                               @RequestParam double costoOrario) {
//        
//		log.info("richiamato metodo POST addCategoria");
//        ResponseEntity<String> response = service.addCategoria(nome, costoOrario);
//        
//        return response;
//    }
}
