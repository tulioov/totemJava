package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.entity.BarcoMonitoracao;
import com.totem.exception.CustomErrorException;
import com.totem.repository.BarcoMonitoracaoRepository;

@Service
public class BarcoMonitoracaoService {
	
	
	@Autowired
	UsuarioService usuarioService;

	@Autowired
    private BarcoMonitoracaoRepository barcoMonitoracaoRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<BarcoMonitoracao> listar(String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		return barcoMonitoracaoRepository.findAll();
	}
	
	public BarcoMonitoracao findById (Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return barcoMonitoracaoRepository.findById(id).get();
	}
	
	public BarcoMonitoracao salvar(BarcoMonitoracao barcoMonitoracao, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		
		barcoMonitoracaoRepository.save(barcoMonitoracao);
		return barcoMonitoracao;
	}
	
	public BarcoMonitoracao delete(Long id, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		BarcoMonitoracao BarcoMonitoracao = barcoMonitoracaoRepository.findById(id).get();
		barcoMonitoracaoRepository.delete(BarcoMonitoracao);
		return BarcoMonitoracao;
	}

}
