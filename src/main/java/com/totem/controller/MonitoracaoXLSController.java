package com.totem.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.totem.dto.FiltroPesquisaMonitoracaoDTO;
import com.totem.service.ExcelService;

@Controller
@RequestMapping("/monitoracaoXLS")
public class MonitoracaoXLSController {

	@Autowired
	ExcelService ExcelService;
	
	@PostMapping("/listarMonitoracaoByFiltroXLS/")
	public ResponseEntity<?> listarMonitoracaoByFiltro(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @RequestBody FiltroPesquisaMonitoracaoDTO filtroPesquisaMonitoracaoDTO) {
		ByteArrayInputStream in = ExcelService.geraPlanilhaCompleta(emailUsuario, filtroPesquisaMonitoracaoDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Relatorio.xls");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
	}


}
