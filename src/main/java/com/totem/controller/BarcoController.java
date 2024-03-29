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

import com.totem.dto.BarcoDTO;
import com.totem.service.BarcoService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/barco")
public class BarcoController {

	@Autowired
	BarcoService barcoService;

	@PostMapping("/salvar")
	public @ResponseBody ResponseEntity<RetornoDTO> salvar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @Valid @RequestBody BarcoDTO barcoDTO) {

		return ResponseEntityUtil.defaultResponse(barcoService.salvar(barcoDTO, emailUsuario));
	}

	@GetMapping("/findById/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> buscarDadosPorId(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(barcoService.findById(id, emailUsuario));
	}

	@GetMapping("/listar")
	public @ResponseBody ResponseEntity<RetornoDTO> listar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario) {
		return ResponseEntityUtil.defaultResponse(barcoService.listar(emailUsuario));
	}
	
	@GetMapping("/findByDtDeleteIsNull")
	public @ResponseBody ResponseEntity<RetornoDTO> findByDtDeleteIsNull(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario) {
		return ResponseEntityUtil.defaultResponse(barcoService.findByDtDeleteIsNull(emailUsuario));
	}
	
	@GetMapping("/escolhaBarcoByNFC/{nfc}")
	public @ResponseBody ResponseEntity<RetornoDTO> escolhaBarcoByNFC(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("nfc") String nfc) {
		return ResponseEntityUtil.defaultResponse(barcoService.listar(emailUsuario, nfc, "nfc"));
	}
	
	@GetMapping("/escolhaBarcoByIdUsuario/{iUsuario}")
	public @ResponseBody ResponseEntity<RetornoDTO> escolhaBarcoByIdUsuario(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("iUsuario") String iUsuario) {
		return ResponseEntityUtil.defaultResponse(barcoService.listar(emailUsuario, iUsuario, "idUsuario"));
	}

	@DeleteMapping("/deletar/{id}")
	public @ResponseBody ResponseEntity<RetornoDTO> deletar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @PathVariable("id") Long id) {
		return ResponseEntityUtil.defaultResponse(barcoService.delete(id, emailUsuario));
	}

}
