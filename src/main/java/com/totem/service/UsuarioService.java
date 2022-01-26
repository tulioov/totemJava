package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.entity.Usuario;
import com.totem.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
    private UsuarioRepository usuarioRepository;
	

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById (Long id) {
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario salvar(Usuario usuario) {
		
		usuarioRepository.save(usuario);
		return usuario;
	}

}
