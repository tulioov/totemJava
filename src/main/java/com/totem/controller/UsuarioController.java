package com.totem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.entity.Usuario;
import com.totem.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("/salvar")
	public @ResponseBody Usuario salvar( @RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
	}
	
	@RequestMapping("/listar")
	public @ResponseBody ResponseEntity<List<Usuario>> listar(){
        return new ResponseEntity<>(usuarioService.listar(), new HttpHeaders(), HttpStatus.OK);
	}
	
}
