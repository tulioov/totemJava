package com.totem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginasController {
	
	@RequestMapping("/")
	public String homePrincipal(Model model){
		model.addAttribute("nome" , "OKEAN");
		return "header";
	}
	
	@RequestMapping("/cadastroUsuario")
	public String cadastroUsuario(Model model){
		model.addAttribute("nome" , "OKEAN");
		return "cadastroUsuario";
	}
	
	@RequestMapping("/monitoramento")
	public String monitoramento(Model model){
		model.addAttribute("nome" , "OKEAN");
		return "monitoramento";
	}
}
