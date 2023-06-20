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

import com.totem.entity.Atividade;
import com.totem.service.AtividadeService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {

	@Autowired
	AtividadeService atividadeService;

	@PostMapping("/salvar")
	public @ResponseBody ResponseEntity<RetornoDTO> salvar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @Valid @RequestBody Atividade atividade) {

		return ResponseEntityUtil.defaultResponse(atividadeService.salvar(atividade, emailUsuario));
	}

	@GetMapping("/findById/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> buscarDadosPorId(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(atividadeService.findById(id, emailUsuario));
	}

	@GetMapping("/listar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario) {
		return ResponseEntityUtil.defaultResponse(atividadeService.listar(emailUsuario));
	}
	
	@GetMapping("/listarAtividadeByLocalId/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> listarAtividadeByLocalId(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(atividadeService.listarAtividadeByLocalId(emailUsuario,id));
	}

	@DeleteMapping("/deletar/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> deletar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(atividadeService.delete(id, emailUsuario));
	}
	
	@GetMapping("/listarAtividadeByLocalIdEspecIdBarcoTemplateId/{localId}/{idUsuario}/{barcotemplateId}")
	public @ResponseBody ResponseEntity<RetornoDTO> listarAtividadeByLocalIdEspecIdBarcoTemplateId(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, 
			@PathVariable("localId") Long localId,
			@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("barcotemplateId") Long barcotemplateId) {
		return ResponseEntityUtil.defaultResponse(atividadeService.listarAtividadeByLocalIdEspecIdBarcoTemplateId(emailUsuario,localId,idUsuario,barcotemplateId));
	}

}
