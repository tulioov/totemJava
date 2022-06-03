package com.totem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totem.entity.Barco;
import com.totem.entity.Monitoracao;
import com.totem.exception.CustomErrorException;
import com.totem.repository.BarcoRepository;

@Service
public class BarcoService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	MonitoracaoService monitoracaoService;
	
	@Autowired
    private BarcoRepository barcoRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<Barco> listar(String emailUsuario) {
//		if(!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
//		}
		return barcoRepository.findAll();
	}
	
	public Barco findById (Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return barcoRepository.findById(id).get();
	}
	
	@Transactional
	public Barco salvar(Barco barco, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED,ERRO_PERMISSAO);
		}
		
		barco.setDtInicio(new Date());
		barcoRepository.save(barco);
		
		return barco;
	}
	
	public Barco delete(Long id, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		Barco barco = barcoRepository.findById(id).get();
//		Monitoracao monitoracao = monitoracaoService.findByBarco(barco.getId(), emailUsuario);
		
//		if(monitoracao != null) {
//			monitoracaoService.delete(id, emailUsuario);
//		}
		
		barcoRepository.delete(barco);
		return barco;
	}

}
