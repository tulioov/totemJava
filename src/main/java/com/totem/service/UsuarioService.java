package com.totem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.dto.UsuarioDTO;
import com.totem.entity.Etapa;
import com.totem.entity.Usuario;
import com.totem.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
    private EtapaService etapaService;
	

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById (Long id) {
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario salvar(UsuarioDTO usuarioDTO) {
		
		List<Etapa> etapaList = new ArrayList<>();
		Usuario usuario = new Usuario();
		
		for (Long codEtapa : usuarioDTO.getEtapaList()) {
			etapaList.add(etapaService.findById(codEtapa));
		}
		
		BeanUtils.copyProperties(usuarioDTO, usuario);
		usuario.setEtapaList(etapaList);
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario delete(Long id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
		return usuario;
	}

}
