package com.corsojava.springcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.springcrud.model.Pizza;
import com.corsojava.springcrud.repository.PizzaRepository;

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
		model.addAttribute("pizze", repository.getReferenceById(id));
		return "/pizze/show";
	}
	
}
