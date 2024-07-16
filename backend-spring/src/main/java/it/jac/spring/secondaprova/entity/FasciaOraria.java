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
@Table(name = "fascie_orarie")
public class FasciaOraria {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		private Long id;
		
		@NonNull
		@Column(name = "fascia_oraria", nullable = false, length = 20, unique = true)
		private String fasciaOraria;
	
}
