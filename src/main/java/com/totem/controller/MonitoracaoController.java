package com.totem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.dto.BarcoMonitoracaoDTO;
import com.totem.dto.FiltroPesquisaMonitoracaoDTO;
import com.totem.dto.MonitoracaoAvulsaDTO;
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
	
	@PostMapping("/salvarMonitoracaoAvulsa")
	public @ResponseBody ResponseEntity<RetornoDTO> salvarMonitoracaoAvulsa(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario,
			@Valid @RequestBody MonitoracaoAvulsaDTO monitoracaoAvulsaDTO) {

		return ResponseEntityUtil.defaultResponse(monitoracaoService.salvarMonitoracaoAvulsa(monitoracaoAvulsaDTO, emailUsuario));
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
	
	@GetMapping("/listarHoraAvulsaByBarcoId/{barcoId}")
	public @ResponseBody ResponseEntity<RetornoDTO> listarHoraAvulsa(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("barcoId") Long barcoId) {
		return ResponseEntityUtil.defaultResponse(monitoracaoService.listarHoraAvulsaByBarcoId(emailUsuario,barcoId));
	}
	
	@PostMapping("/listarMonitoracaoByUsuarios/")
	public @ResponseBody ResponseEntity<RetornoDTO> listarMonitoracaoByUsuarios(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @RequestBody FiltroPesquisaMonitoracaoDTO filtroPesquisaMonitoracaoDTO) {
		return ResponseEntityUtil.defaultResponse(monitoracaoService.listarMonitoracaoByUsuarios(emailUsuario, filtroPesquisaMonitoracaoDTO));
	}

}
