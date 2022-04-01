package com.totem.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.totem.util.Utilities;

@Controller
public class PaginasController {
	
	
	@GetMapping("/login")
	public String homePrincipal(Model model){
		return "login";
	}
	
	@GetMapping("/cadastroUsuario")
	public String cadastroUsuario(Model model){
		model.addAttribute("titulo" , "Cadastro Usuario");
		return "cadastroUsuario";
	}
	
	@GetMapping(value = {"/", "/monitoramento"})
	public String home(Model model, @AuthenticationPrincipal OidcUser principal){
		model.addAttribute("claims", Utilities.filterClaims(principal));
		model.addAttribute("titulo" , "Apontamento de horas");
		return "monitoramento";
	}
	
	@GetMapping("/controlTotens")
	public String nfc(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "controlTotens";
	}
	
	@GetMapping("/cadastroBarco")
	public String cadastroBarco(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroBarco";
	}
	
	@GetMapping("/cadasdroEtapas")
	public String cadasdroEtapas(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadasdroEtapas";
	}
	
	@GetMapping("/cadastroSubAtividade")
	public String cadastroGruposAtividades(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroSubAtividade";
	}
	
	@GetMapping("/cadastroAtividade")
	public String cadastroAtividade(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroAtividade";
	}
	
	@GetMapping("/cadastroItens")
	public String cadastroGrupoMaterial(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroItens";
	}
	
	@GetMapping("/cadastroMaterial")
	public String cadastroMaterial(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "cadastroMaterial";
	}
	
	@GetMapping("/relatorio")
	public String relatorio(Model model){
		model.addAttribute("titulo" , "Controle de Tontens");
		return "relatorio";
	}
	
}
