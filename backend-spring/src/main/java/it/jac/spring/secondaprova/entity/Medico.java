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
@Table(name = "medico")
public class Medico {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@NonNull
	@Column(name = "NOME", nullable = false, length = 20)
	private String nome;
	
	@NonNull
	@Column(name = "INDIRIZZO_STUDIO", nullable = false, length = 50)
	private String indirizzoStudio;
	
	@NonNull
	@Column(name = "SPECIALITA", nullable = false, length = 30)
	private String specialita;
	
	@NonNull
	@Column(name = "PREZZO_VISITA", length = 12, nullable = false)
	private Double prezzoVisita;
	
}
