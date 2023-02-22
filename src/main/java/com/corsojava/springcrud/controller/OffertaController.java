package com.corsojava.springcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.springcrud.model.Offerta;
import com.corsojava.springcrud.model.Pizza;
import com.corsojava.springcrud.repository.OffertaRepository;
import com.corsojava.springcrud.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	OffertaRepository offertaRepository;
	
	@GetMapping()
	public String index(Model model) {
		List<Offerta> elencoOfferte = offertaRepository.findAll();
		model.addAttribute("listaOfferte", elencoOfferte);
		return "/offerte/index";
	}
	
	@GetMapping("/insert")		// PER GESTIRE LE RICHIESTE "GET" DI /offerte/insert?pizzaId=xxx
	public String insert(
			@RequestParam(name="pizzaId", required=true) Integer pizzaId,
			Model model
	) throws Exception {
		Offerta offerta = new Offerta();
		
		Optional<Pizza> result = pizzaRepository.findById(pizzaId);
		if(result.isPresent())
			offerta.setPizza(result.get());
		else
			throw new Exception("Non esiste la pizza su cui vuoi mettere l'offerta! " +pizzaId);
		
		model.addAttribute("offerta", offerta);
		return "/offerte/insert";
	}
	
	@PostMapping("/insert")
	public String store(
			@Valid @ModelAttribute("offerta") Offerta formOfferta,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "offerte/insert";
		
		offertaRepository.save(formOfferta);
		return "redirect:/offerte";
	}

}