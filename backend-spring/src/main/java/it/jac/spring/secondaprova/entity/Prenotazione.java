package it.jac.spring.secondaprova.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data // GETTER E SETTER
@NoArgsConstructor // COSTRUTTORE VUOTO
@RequiredArgsConstructor // COSTRUTTORE CON ARGOMENTI @NONNULL
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Long id;
    
	@NonNull
    @Column(name = "GIORNO", length = 12, nullable = false)
    private LocalDate giorno;
	
	
	@NonNull
    @Column(name = "FASCIA_ORARIA", length = 20, nullable = false)
    private String fasciaOraria;
    

	@NonNull
    @Column(name = "COD_CONFERMA", length = 100, nullable = false, unique = true)
    private String codConferma;
	
    // FK:
    
    @ManyToOne
	@NonNull
    @JoinColumn(name = "ID_MEDICO", referencedColumnName = "ID", nullable = false)
    private Medico idMedico;
    
}
