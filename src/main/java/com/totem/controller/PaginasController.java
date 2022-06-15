package com.totem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.totem.service.PaginaService;

@Controller
public class PaginasController {
	
	@Autowired
	PaginaService paginaService;
	
	
	@GetMapping("/login")
	public String homePrincipal(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		return "login";
	}
	
	@GetMapping("/cadastroFeriado")
	public String cadastroFeriado(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		return "cadastroFeriado";
	}
	
	@GetMapping("/cadastroUsuario")
	public String cadastroUsuario(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Cadastro Usuario");
		return "cadastroUsuario";
	}
	
	@GetMapping(value = {"/", "/monitoramento"})
	public String home(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		return "monitoramento";
	}
	
	@GetMapping("/controlTotens")
	public String nfc(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "controlTotens";
	}
	
	@GetMapping("/cadastroBarco")
	public String cadastroBarco(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroBarco";
	}
	
	@GetMapping("/cadasdroEtapas")
	public String cadasdroEtapas(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadasdroEtapas";
	}
	
	@GetMapping("/cadastroSubAtividade")
	public String cadastroGruposAtividades(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroSubAtividade";
	}
	
	@GetMapping("/cadastroAtividade")
	public String cadastroAtividade(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroAtividade";
	}
	
	@GetMapping("/cadastroItens")
	public String cadastroGrupoMaterial(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroItens";
	}
	
	@GetMapping("/cadastroMaterial")
	public String cadastroMaterial(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroMaterial";
	}
	
	@GetMapping("/relatorio")
	public String relatorio(Model model, @AuthenticationPrincipal OidcUser principal){
		paginaService.control(principal, model);
		model.addAttribute("titulo" , "Controle de Tontens");
		return "relatorio";
	}
	
}
