package com.corsojava.springcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.springcrud.model.Ingrediente;
import com.corsojava.springcrud.repository.IngredienteRepository;


@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
	@Autowired
	IngredienteRepository ingredienteRepository;
	
	@GetMapping()
	public String index(Model model) {
		List<Ingrediente> listaIngredienti = ingredienteRepository.findAll();
		model.addAttribute("elencoIngredienti", listaIngredienti);
		return "/ingredienti/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Ingrediente ingrediente=new Ingrediente();
		
		model.addAttribute("ingrediente", ingrediente);

		return "/ingredienti/create";
	}
	
	@PostMapping("/create")
	public String store(
		@ModelAttribute("ingrediente") Ingrediente formIngrediente, Model model) {
		
		ingredienteRepository.save(formIngrediente);
		
		return "redirect:/ingredienti";	
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Ingrediente> ingrediente= ingredienteRepository.findById(id);
		if(ingrediente.isEmpty()) {
			return "redirect:/ingredienti";
		}
		model.addAttribute("ingrediente",ingrediente.get());
		return "ingredienti/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@ModelAttribute("ingrediente") Ingrediente formIngrediente, Model model) {
		ingredienteRepository.save(formIngrediente);
		return "redirect:/ingredienti";
		
	}

}
