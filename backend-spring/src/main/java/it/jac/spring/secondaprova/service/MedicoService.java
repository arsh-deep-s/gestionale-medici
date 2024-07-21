package it.jac.spring.secondaprova.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.jac.spring.secondaprova.entity.Medico;
import it.jac.spring.secondaprova.repository.MedicoRepository;

@Service
public class MedicoService {

	private static Logger log = LogManager.getLogger(MedicoService.class);
	
	private MedicoRepository repository;
	
    @Autowired
    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }
    
    // GET MEDICI
    
    public List<Medico> findAll() {
    	log.info("richiamato metodo service findAll");
        return (List<Medico>) repository.findAll();
    }
    
    // ADD MEDICO
    
    public ResponseEntity<String> addMedico(String nome,
    										String indirizzoStudio,
    										String specialita,
    										Double prezzoVisita) {
    	
    	log.info("richiamato metodo service addMedico");
    	
        try {
        	
            Medico medico = new Medico(nome, indirizzoStudio, specialita, prezzoVisita);
            
            repository.save(medico);
            
            return ResponseEntity.ok("Medico aggiunto con successo");
            
        } catch (Exception e) {
            String errorMessage = "Errore durante l'aggiunta del medico";
            log.info("errore metodo service addCliente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    
    // FIND BY ID MEDICO
    
//    public Optional<Medico> findById(Long id) {
//    	
//    	log.info("richiamato metodo service findById");
//    	
//    	return repository.findById(id);
//    	
//    }

}
