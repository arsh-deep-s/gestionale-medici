package it.jac.spring.secondaprova.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.spring.secondaprova.entity.FasciaOraria;
import it.jac.spring.secondaprova.repository.FasciaOrariaRepository;

@Service
public class FasciaOrariaService {

	private static Logger log = LogManager.getLogger(FasciaOrariaService.class);
	
	private FasciaOrariaRepository repository;
	
    @Autowired
    public FasciaOrariaService(FasciaOrariaRepository repository) {
        this.repository = repository;
    }
    
    // GET ALL FASCE ORARIE
    
    public List<FasciaOraria> findAll() {
    	log.info("richiamato metodo service findAll");
        return (List<FasciaOraria>) repository.findAll();
    }
    
    // FIND FASCIA ORARIA BY ID
    
    public Optional<FasciaOraria> findById(Long id) {
    	
    	log.info("richiamato metodo categoria service findById");
    	
    	return repository.findById(id);
    	
    }

}
