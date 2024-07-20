package it.jac.spring.secondaprova.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.jac.spring.secondaprova.entity.Utente;
import it.jac.spring.secondaprova.repository.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UtenteService {

	private static Logger log = LogManager.getLogger(UtenteService.class);

	private UtenteRepository repository;

	@Autowired
	public UtenteService(UtenteRepository repository) {
		this.repository = repository;
	}
	
//	// SAVE UTENTE
//	
//	public void saveUtente(Utente utente) {	
//		this.repository.save(utente);
//	}
//
//	// ADD CLIENTE
//
//	public ResponseEntity<String> addCliente(String nome, String cognome, String email, String password, String ruolo,
//			String codiceFiscale) {
//
//		log.info("richiamato metodo service addCliente");
//
//		try {
//
//			Utente utente = new Utente(email, password, ruolo, nome, cognome, codiceFiscale);
//
//			repository.save(utente);
//
//			return ResponseEntity.ok("Cliente aggiunto con successo");
//
//		} catch (Exception e) {
//			String errorMessage = "Errore durante l'aggiunta del cliente";
//			log.info("errore metodo service addCliente");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//		}
//	}
//
//	// FIND BY ID CLIENTE
//
//	public Optional<Utente> findById(Long id) {
//
//		log.info("richiamato metodo service findById");
//
//		return repository.findById(id);
//
//	}
//
//	// FIND BY EMAIL CLIENTE
//
//	public Utente findByEmail(String email) {
//		
//		if (email == null || email.isEmpty()) {
//			throw new IllegalArgumentException("Email cannot be null or empty");
//		}
//
//		log.info("richiamato metodo service findByEmail con email={}", email);
//
//		return repository.findByEmail(email)
//				.orElseThrow(() -> new EntityNotFoundException("Utente not found with email=" + email));
//	}
//
//	// LOGIN CLIENTE
//
//	public ResponseEntity<String> login(String email, String password) {
//
//		log.info("richiamato metodo service findByMail");
//
//		Optional<Utente> clienteOpt = repository.findByEmail(email);
//
//		if (clienteOpt.isPresent()) {
//
//			// SOLO SE E' PRESENTE CONTROLLO LA PASSWORD
//
//			Utente utente = clienteOpt.get();
//
//			if (utente.getPassword().equals(password)) {
//				return ResponseEntity.ok("Login effettuato con successo");
//
//			} else {
//
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password errata");
//			}
//
//		} else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username non trovato");
//		}
//	}
}
