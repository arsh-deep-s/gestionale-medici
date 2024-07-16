package it.jac.spring.secondaprova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.spring.secondaprova.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository <Medico, Long>{

}
