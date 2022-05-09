package com.totem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.service.MonitoracaoService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/monitoracao")
public class MonitoracaoController {

	@Autowired
	MonitoracaoService monitoracaoService;

//	@PostMapping("/salvar")
//	public @ResponseBody ResponseEntity<RetornoDTO> salvar(
//			@RequestHeader(name = "Authorization", required = true) String emailUsuario,
//			@Valid @RequestBody AtividadeDTO atividadeDTO) {
//
//		return ResponseEntityUtil.defaultResponse(monitoracaoService.salvar(atividadeDTO, emailUsuario));
//	}

	@GetMapping("/findByBarcoId/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> buscarDadosPorBarcoId(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(monitoracaoService.findByBarco(id, emailUsuario));
	}

	@GetMapping("/listar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario) {
		return ResponseEntityUtil.defaultResponse(monitoracaoService.listar(emailUsuario));
	}


}
