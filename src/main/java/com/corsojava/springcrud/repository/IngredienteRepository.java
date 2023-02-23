package com.corsojava.springcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.springcrud.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

}
