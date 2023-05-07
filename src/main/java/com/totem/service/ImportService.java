package com.totem.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.dto.ImportDTO;
import com.totem.entity.Atividade;
import com.totem.entity.Local;
import com.totem.entity.Usuario;

@Service
public class ImportService {

	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AtividadeService atividadeService;

	@Autowired
	private LocalService localService;

	public Object importar(@Valid ImportDTO importDTO, String emailUsuario) {

		if ("usuario".equals(importDTO.getTipoObject())) {
			
			for (Usuario usuario : importDTO.getLstUsuario()) {
				usuarioService.salvar(usuario);
			}
			
		}

		if ("atividade".equals(importDTO.getTipoObject())) {
			
			for (Atividade atividade : importDTO.getLstAtividade()) {
				atividadeService.salvar(atividade, emailUsuario);
			}

		}

		if ("local".equals(importDTO.getTipoObject())) {
			
			for (Local local : importDTO.getLstLocal()) {
				localService.salvar(local, emailUsuario);
			}

		}

		return null;
	}

}
