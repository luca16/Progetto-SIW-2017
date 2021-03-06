package it.uniroma3.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.spring.model.Autore;

import it.uniroma3.spring.service.AutoreService;


////
@Controller
public class AutoreController {
	@Autowired
	private AutoreService autoreService;
	
	@GetMapping("/nuovoAutore")
	public String showForm(Autore autore){
		return "autoreForm";
	}

	
	@PostMapping("/autore")
	public String checkQaudroInfo(@Valid @ModelAttribute Autore autore,
			BindingResult bindingResult, Model model){
		
		if (bindingResult.hasErrors()) {
            return "autoreForm";
        }
        else {
        	model.addAttribute(autore);
            autoreService.add(autore); 
        }
        return "resultAutore";
		
	}
	
	

	@GetMapping("/listaAutori")
	public String getAutori(Model model){
		
		model.addAttribute("autori", this.autoreService.findAll());
		return "listaAutori";
	}
	
	@GetMapping("/eliminaAutore")
	public String eliminaAutore(Model model){
		model.addAttribute("autore", autoreService.findAll());
		return "gestisciAutori";
	}
	
	@GetMapping("/eliminaA")
	public String eliminaAutore(Model model, @ModelAttribute("id") Long id){
		Autore autore = autoreService.findbyId(id);
		autoreService.delete(autore);
		model.addAttribute("autori", autoreService.findAll());
		return "gestisciAutori";
	}
	
	@GetMapping("/infoAutore")
	public String mostraInfo( Model model, @ModelAttribute("id") Long id){
		
		Autore autore = autoreService.findbyId(id);
		model.addAttribute(autore);
		return "informazioniAutore";
}
}
