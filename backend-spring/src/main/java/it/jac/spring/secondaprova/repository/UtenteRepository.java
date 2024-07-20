package it.jac.spring.secondaprova.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.spring.secondaprova.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{
	Optional<Cliente> findByMail(String mail);

}
