package com.totem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.dto.FeriadoDTO;
import com.totem.service.FeriadoService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/feriado")
public class FeriadoController {

	@Autowired
	FeriadoService feriadoService;

	@PostMapping("/salvar")
	public @ResponseBody ResponseEntity<RetornoDTO> salvar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @Valid @RequestBody FeriadoDTO feriadoDTO) {

		return ResponseEntityUtil.defaultResponse(feriadoService.salvar(feriadoDTO, emailUsuario));
	}

	@GetMapping("/findById/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> buscarDadosPorId(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(feriadoService.findById(id));
	}

	@GetMapping("/listar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario) {
		return ResponseEntityUtil.defaultResponse(feriadoService.listar(emailUsuario));
	}

	@DeleteMapping("/deletar/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> deletar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(feriadoService.delete(id, emailUsuario));
	}

}
