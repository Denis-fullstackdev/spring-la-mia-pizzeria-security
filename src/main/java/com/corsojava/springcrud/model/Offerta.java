package com.corsojava.springcrud.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Offerta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Inserire titolo - Campo obbligatorio")
	@NotEmpty(message="Inserire titolo - Campo obbligatorio")
	@Size(min=10, max=50, message="Il titolo deve essere di minimo 10 caratteri e massimo 50 caratteri")
	private String titolo;
	
	@NotNull(message="Inserire data Inizio offerta - Campo obbligatorio")
	private LocalDate inizioOfferta;
	
	@NotNull(message="Inserire data Fine offerta - Campo obbligatorio")
	private LocalDate fineOfferta;
	
	@NotNull
	@ManyToOne
	private Pizza pizza;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getInizioOfferta() {
		return inizioOfferta;
	}

	public void setInizioOfferta(LocalDate inizioOfferta) {
		this.inizioOfferta = inizioOfferta;
	}

	public LocalDate getFineOfferta() {
		return fineOfferta;
	}

	public void setFineOfferta(LocalDate fineOfferta) {
		this.fineOfferta = fineOfferta;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}
