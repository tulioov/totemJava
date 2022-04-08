package com.totem.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.totem.dto.UsuarioDTO;
import com.totem.entity.Usuario;
import com.totem.util.Utilities;

@Service
public class PaginaService {
	
	@Value("${adm.Default1}")
	private String admDefault1;
	
	@Value("${adm.Default2}")
	private String admDefault2;
	
	@Autowired
	UsuarioService usuarioService;
	
	public void control(OidcUser principal, Model model) {
		Map<String,String> filteredClaims = Utilities.filterClaims(principal);
		String nome = filteredClaims.get("name");
		String email = filteredClaims.get("preferred_username");
		model.addAttribute("nome" , nome);
		model.addAttribute("email" , email);
		Usuario usuario = usuarioService.findByEmail(email);
		
		if(usuario == null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setNome(nome);
			usuarioDTO.setIsAdmin((email.equals(admDefault1)||email.equals(admDefault2))?Boolean.TRUE:Boolean.FALSE);
			usuarioDTO.setEmail(email);
			usuarioDTO.setEspecialidade("Usuário");
			usuarioService.salvar(usuarioDTO, email);
			return;
		}
		
		model.addAttribute("isAdm" , usuario.getIsAdmin()==null?Boolean.FALSE:usuario.getIsAdmin());
		
	}
}
