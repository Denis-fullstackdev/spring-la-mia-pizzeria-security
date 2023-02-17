package com.corsojava.springcrud.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="pizze")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Inserire nome - Campo obbligatorio")
	@NotEmpty(message="Inserire nome - Campo obbligatorio")
	@NotBlank(message="Inserire nome - Campo obbligatorio")
	@Size(min=5, max=40, message="Il nome deve essere di minimo :min caratteri e massimo :max")
	private String nome;
	
	@NotNull(message="Inserire descrizione - Campo obbligatorio")
	@NotEmpty(message="Inserire descrizione - Campo obbligatorio")
	private String descrizione;
	private String foto;
	private double prezzo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
