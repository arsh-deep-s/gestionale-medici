package it.jac.spring.secondaprova.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.spring.secondaprova.entity.Medico;
import it.jac.spring.secondaprova.entity.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository <Prenotazione, Long>{
	List<Prenotazione> findByGiornoAndMedico(LocalDate giorno, Medico medico);

}
