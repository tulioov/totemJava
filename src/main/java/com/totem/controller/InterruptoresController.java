package com.totem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.totem.service.BotaoService;

@Controller
public class InterruptoresController {
	
	@Autowired
	BotaoService botaoService;
	
	@RequestMapping("/ligaDesliga/{status}")
	public ResponseEntity<Object> ligarOuDesligarInterruptor(@PathVariable("status") String status){
        return botaoService.ligaDesliga(status);
	}
	
	@RequestMapping("/ligaDesligaAuto/{status}")
	public ResponseEntity<Object> ligarOuDesligarInterruptorAuto(@PathVariable("status") String status){
        return botaoService.ligaDesligaAuto(status);
	}
	
	@RequestMapping("/botaoBombaCasa")
	public ResponseEntity<Object> botaoBombaCasa() {
		return botaoService.statusBombaCasa();
	}
	
	@RequestMapping("/botaoBombaCasaAuto")
	public ResponseEntity<Object> botaoBombaCasaAuto() {
		return botaoService.statusBombaCasaAuto();
	}
	
}
