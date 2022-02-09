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

import com.totem.entity.SubAtividade;
import com.totem.service.SubAtividadeService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/subAtividade")
public class SubAtividadeController {

	@Autowired
	SubAtividadeService subAtividadeService;

	@PostMapping("/salvar")
	public @ResponseBody ResponseEntity<RetornoDTO> salvar(
			@RequestHeader(name = "Authorization", required = true) Long idUsuario, @Valid @RequestBody SubAtividade subAtividade) {

		return ResponseEntityUtil.defaultResponse(subAtividadeService.salvar(subAtividade));
	}

	@GetMapping("/findById/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> buscarDadosPorId(
			@RequestHeader(name = "Authorization", required = true) Long idUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(subAtividadeService.findById(id));
	}

	@GetMapping("/listar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(
			@RequestHeader(name = "Authorization", required = true) Long idUsuario) {
		return ResponseEntityUtil.defaultResponse(subAtividadeService.listar());
	}

	@DeleteMapping("/deletar/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> deletar(
			@RequestHeader(name = "Authorization", required = true) Long idUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(subAtividadeService.delete(id));
	}

}