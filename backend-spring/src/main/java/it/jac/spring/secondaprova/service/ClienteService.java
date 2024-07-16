package it.jac.spring.secondaprova.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.jac.spring.secondaprova.entity.Cliente;
import it.jac.spring.secondaprova.repository.ClienteRepository;

@Service
public class ClienteService {

	private static Logger log = LogManager.getLogger(ClienteService.class);
	
	private ClienteRepository repository;
	
    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }
    
    // GET CLIENTI
    
    public List<Cliente> findAll() {
    	log.info("richiamato metodo service findAll");
        return (List<Cliente>) repository.findAll();
    }
    
    // ADD CLIENTE
    
    public ResponseEntity<String> addCliente(String nome,
    										String cognome,
    										String mail,
    										String password,
    										String codiceFiscale) {
    	
    	log.info("richiamato metodo service addCliente");
    	
        try {
        	
            Cliente cliente = new Cliente(nome, cognome, mail, password, codiceFiscale);
            
            repository.save(cliente);
            
            return ResponseEntity.ok("Cliente aggiunto con successo");
            
        } catch (Exception e) {
            String errorMessage = "Errore durante l'aggiunta del cliente";
            log.info("errore metodo service addCliente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    
    // FIND BY ID CLIENTE
    
    public Optional<Cliente> findById(Long id) {
    	
    	log.info("richiamato metodo service findById");
    	
    	return repository.findById(id);
    	
    }
    
    // LOGIN CLIENTE
    
    public ResponseEntity<String> login(String mail, String password) {
    	
    	log.info("richiamato metodo service findByMail");
    	
    	Optional<Cliente> clienteOpt = repository.findByMail(mail);
    	
    	if (clienteOpt.isPresent()) {
    		
    		// SOLO SE E' PRESENTE CONTROLLO LA PASSWORD
    		
    		Cliente cliente = clienteOpt.get();
    		
    		if (cliente.getPassword().equals(password)) {
                return ResponseEntity.ok("Login effettuato con successo");
                
            } else {
            	
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password errata");
            }
    		
    	} else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username non trovato");
        }    	
    } 
}
