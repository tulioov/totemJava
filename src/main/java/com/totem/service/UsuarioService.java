package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
    private UsuarioRepository usuarioRepository;
	

	public List<?> listar() {
		
		return usuarioRepository.findAll();

	}

}
