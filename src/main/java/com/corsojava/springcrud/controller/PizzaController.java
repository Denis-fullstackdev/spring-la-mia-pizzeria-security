package com.corsojava.springcrud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.springcrud.model.Pizza;
import com.corsojava.springcrud.repository.PizzaRepository;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	private PizzaRepository repository;
	
	@GetMapping
	public String menuController(Model model) {
		model.addAttribute("pizze", repository.findAll());
		return "/pizze/index";
	}
	
//	@GetMapping
//	public String index(Model model) {
//		List<Pizza> listaPizze = new ArrayList<>();
//		listaPizze.add(new Pizza());
//		model.addAttribute("list", listaPizze);
//		return "/pizze/index";
//	}
}
