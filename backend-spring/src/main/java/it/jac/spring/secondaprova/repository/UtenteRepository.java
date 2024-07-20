package it.jac.spring.secondaprova.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.spring.secondaprova.entity.Utente;

@Repository
public interface UtenteRepository extends JpaRepository <Utente, Long>{
	Optional<Utente> findByEmail(String email);

}
