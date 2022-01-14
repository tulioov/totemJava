package com.totem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginasController {
	
	@RequestMapping("/")
	public String homePrincipal(Model model){
		model.addAttribute("titulo" , "OKEAN");
		return "header";
	}
	
	@RequestMapping("/cadastroUsuario")
	public String cadastroUsuario(Model model){
		model.addAttribute("titulo" , "Cadastro Usuario");
		return "cadastroUsuario";
	}
	
	@RequestMapping("/monitoramento")
	public String monitoramento(Model model){
		model.addAttribute("titulo" , "Apontamento de horas");
		return "monitoramento";
	}
	
	@RequestMapping("/controlTotens")
	public String nfc(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "controlTotens";
	}
	
	@RequestMapping("/cadastroBarco")
	public String cadastroBarco(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroBarco";
	}
	
	@RequestMapping("/cadasdroEtapas")
	public String cadasdroEtapas(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadasdroEtapas";
	}
	
	@RequestMapping("/cadastroGruposAtividades")
	public String cadastroGruposAtividades(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroGruposAtividades";
	}
	
	@RequestMapping("/cadastroAtividade")
	public String cadastroAtividade(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroAtividade";
	}
	
	@RequestMapping("/cadastroGrupoMaterial")
	public String cadastroGrupoMaterial(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroGrupoMaterial";
	}
	
	@RequestMapping("/cadastroMaterial")
	public String cadastroMaterial(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroMaterial";
	}
	
	@RequestMapping("/relatorio")
	public String relatorio(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "relatorio";
	}
	
}
