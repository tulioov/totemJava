package com.totem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.entity.Usuario;
import com.totem.service.UsuarioService;

import util.AjaxResponse;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@RequestBody Usuario usuario) {

		try {
			usuarioService.salvar(usuario);
			return AjaxResponse.generateResponse("Success", HttpStatus.OK, usuario);
		} catch (Exception e) {
			return AjaxResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}

	}

	@GetMapping("/listar")
	public ResponseEntity<Object> listar() {
		try {
			return AjaxResponse.generateResponse("Success", HttpStatus.OK, usuarioService.listar());
		} catch (Exception e) {
			return AjaxResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

}
