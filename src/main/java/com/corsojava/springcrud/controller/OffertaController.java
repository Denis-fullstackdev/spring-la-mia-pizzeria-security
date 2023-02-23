package com.corsojava.springcrud.controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.springcrud.model.Offerta;
import com.corsojava.springcrud.model.Pizza;
import com.corsojava.springcrud.repository.OffertaRepository;
import com.corsojava.springcrud.repository.PizzaRepository;

import jakarta.persistence.EntityNotFoundException;


@Controller
@RequestMapping("/offerte")
public class OffertaController {
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	OffertaRepository offertaRepository;
	
	@GetMapping("/create")
	public String create(
			@RequestParam(name="pizzaId", required=true) Integer pizzaId,
			Model model
	) throws Exception {
		Offerta offerta = new Offerta();
		
		try {
			Pizza pizza = pizzaRepository.getReferenceById(pizzaId);
			offerta.setPizza(pizza);
		} catch (EntityNotFoundException e) {
			throw new Exception("Non è presente l'oggetto Pizza. Id=" + pizzaId);
		}
		
		model.addAttribute("offerta", offerta);
		return "/offerte/create";
	}
	
	@PostMapping("/create")
	public String store(
			@ModelAttribute("offerta") Offerta formOfferta,
			Model model) {
		
		offertaRepository.save(formOfferta);
		return "redirect:/pizze/" + formOfferta.getPizza().getId();
	}

}