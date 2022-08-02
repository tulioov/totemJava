package com.totem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.dto.BarcoMonitoracaoDTO;
import com.totem.service.MonitoracaoService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/monitoracao")
public class MonitoracaoController {

	@Autowired
	MonitoracaoService monitoracaoService;

	@PostMapping("/salvarBarcoMonitoracao")
	public @ResponseBody ResponseEntity<RetornoDTO> salvar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario,
			@Valid @RequestBody BarcoMonitoracaoDTO monitoracaoDTO) {

		return ResponseEntityUtil.defaultResponse(monitoracaoService.salvarNovaBarcoMonitoracao(monitoracaoDTO, emailUsuario));
	}
	
	@PostMapping("/continuarPausarFinalizar")
	public @ResponseBody ResponseEntity<RetornoDTO> continuarPausarFinalizar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario,
			@Valid @RequestBody BarcoMonitoracaoDTO monitoracaoDTO) {

		return ResponseEntityUtil.defaultResponse(monitoracaoService.continuarPausarFinalizar(monitoracaoDTO, emailUsuario));
	}

	@GetMapping("/listar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario) {
		return ResponseEntityUtil.defaultResponse(monitoracaoService.listar(emailUsuario));
	}


}
