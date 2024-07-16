package it.jac.spring.secondaprova.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data // GETTER E SETTER
@NoArgsConstructor // COSTRUTTORE VUOTO
@RequiredArgsConstructor // COSTRUTTORE CON ARGOMENTI @NONNULL
@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@NonNull
	@Column(name = "NOME", nullable = false, length = 20)
	private String nome;
	
	@NonNull
	@Column(name = "COGNOME", nullable = false, length = 20)
	private String cognome;
	
	@NonNull
	@Column(name = "MAIL", nullable = false, length = 30)
	private String mail;
	
	@NonNull
	@Column(name = "PASSWORD", nullable = false, length = 30)
	private String password;
	
	@NonNull
	@Column(name = "CODICE_FISCALE", length = 16, nullable = false, unique = true)
	private String codiceFiscale;
	
}
