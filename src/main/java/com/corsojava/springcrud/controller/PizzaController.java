package com.corsojava.springcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.springcrud.model.Pizza;
import com.corsojava.springcrud.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	private PizzaRepository repository;
	
	@GetMapping
	public String menuController(@RequestParam(name="keyword", required=false) String keyword, Model model) {
		List<Pizza> filtro;
		
		if ( keyword != null && !keyword.isEmpty() )
			filtro = repository.findByNomeLike("%" + keyword + "%");
		else
			filtro = repository.findAll();
		model.addAttribute("pizze", filtro);
		return "pizze/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("pizza", repository.getReferenceById(id));
		return "pizze/show";
	}
	
	@GetMapping("/create")		// PER GESTIRE LE RICHIESTE "GET" DI /pizze/new
	public String create(Model model) {
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		return "pizze/create";
	}
	
	@PostMapping("/create")	// PER GESTIRE LE RICHIESTE "POST" DI /pizze/new
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors())
			return "/pizze/create";
		
		repository.save(formPizza);
		return "redirect:/pizze";
	}
	
}
