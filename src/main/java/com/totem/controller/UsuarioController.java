package com.totem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.entity.Usuario;
import com.totem.service.UsuarioService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/salvar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(@RequestHeader(name = "Authorization", required = true) Long idUsuario , @RequestBody Usuario usuario){
        return  ResponseEntityUtil.defaultResponse(usuarioService.salvar(usuario));
	}
	
	
	@GetMapping("/listar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(@RequestHeader(name = "Authorization", required = true) Long idUsuario){
		return ResponseEntityUtil.defaultResponse(usuarioService.listar());
	}
	
	
//	@GetMapping("/{id}")
//	public @ResponseBody ResponseEntity<RetornoDTO> buscarDadosPorId(
//			@RequestHeader(name = "Authorization", required = true) Long idUsuario, @PathVariable("id") Long id) {
//		return ResponseEntityUtil.defaultResponse(configuracaoService.findById(id));
//	}
	
}
