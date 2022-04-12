package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.entity.Etapa;
import com.totem.exception.CustomErrorException;
import com.totem.repository.EtapaRepository;

@Service
public class EtapaService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
    private EtapaRepository etapaRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<Etapa> listar(String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return etapaRepository.findAll();
	}
	
	public Etapa findById (Long id) {
		return etapaRepository.findById(id).get();
	}
	
	public Etapa salvar(Etapa etapa, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		etapaRepository.save(etapa);
		return etapa;
	}
	
	public Etapa delete(Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		Etapa etapa = etapaRepository.findById(id).get();
		etapaRepository.delete(etapa);
		return etapa;
	}

}
