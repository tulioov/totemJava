package com.totem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totem.dto.ImportDTO;
import com.totem.entity.Usuario;
import com.totem.service.ImportService;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@Controller
@RequestMapping("/import")
public class ImportController {

	@Autowired
	ImportService importService;

	@PostMapping("/planilha")
	public @ResponseBody ResponseEntity<RetornoDTO> salvar(
			@RequestHeader(name = "Authorization", required = true) String emailUsuario, @Valid @RequestBody ImportDTO importDTO) {
		

		return ResponseEntityUtil.defaultResponse(importService.importar(importDTO, emailUsuario));
	}
}
