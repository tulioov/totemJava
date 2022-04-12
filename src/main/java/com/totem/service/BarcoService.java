package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.entity.Barco;
import com.totem.exception.CustomErrorException;
import com.totem.repository.BarcoRepository;

@Service
public class BarcoService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
    private BarcoRepository barcoRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<Barco> listar(String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return barcoRepository.findAll();
	}
	
	public Barco findById (Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return barcoRepository.findById(id).get();
	}
	
	public Barco salvar(Barco barco, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED,ERRO_PERMISSAO);
		}
		
		barcoRepository.save(barco);
		return barco;
	}
	
	public Barco delete(Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		Barco barco = barcoRepository.findById(id).get();
		barcoRepository.delete(barco);
		return barco;
	}

}
