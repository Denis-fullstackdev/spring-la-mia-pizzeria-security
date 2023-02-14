package com.corsojava.springcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.springcrud.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
