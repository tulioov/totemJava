package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.entity.StatusMonitoracao;
import com.totem.exception.CustomErrorException;
import com.totem.repository.StatusMonitoracaoRepository;

@Service
public class StatusMonitoracaoService {
	
	@Autowired
    private StatusMonitoracaoRepository statusMonitoracaoRepository;
	
	@Autowired
    private UsuarioService usuarioService;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  

	public List<StatusMonitoracao> listar(String emailUsuario) {
		return statusMonitoracaoRepository.findAll();
	}
	
	public StatusMonitoracao findById (Long id) {
		return statusMonitoracaoRepository.findById(id).get();
	}
	
	public StatusMonitoracao findByConstanteCampo(String constanteCampo) {
		return statusMonitoracaoRepository.findByConstanteCampo(constanteCampo);
	}
	
	
	public StatusMonitoracao salvar(StatusMonitoracao menuPausa, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		menuPausa.setConstanteCampo("PAUSA_"+menuPausa.getConstanteCampo());
		statusMonitoracaoRepository.save(menuPausa);
		return menuPausa;
	}
	
	public StatusMonitoracao delete(Long id, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		StatusMonitoracao menuPausa = statusMonitoracaoRepository.findById(id).get();
		statusMonitoracaoRepository.save(menuPausa);
		return menuPausa;
	}

}
