package com.totem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.UsuarioDTO;
import com.totem.entity.Etapa;
import com.totem.entity.Usuario;
import com.totem.exception.CustomErrorException;
import com.totem.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EtapaService etapaService;

	public List<Usuario> listar(String emailUsuario) {
		if(!isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return usuarioRepository.findAll();
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).get();
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public Usuario salvar(UsuarioDTO usuarioDTO, String emailUsuario) {
		
		if(!isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		Usuario usuario = new Usuario();

		addListEtapaInUsuario(usuarioDTO, usuario);

		BeanUtils.copyProperties(usuarioDTO, usuario);

		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario salvar(UsuarioDTO usuarioDTO) {
		
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDTO, usuario);
		usuarioRepository.save(usuario);
		return usuario;
	}

	private void addListEtapaInUsuario(UsuarioDTO usuarioDTO, Usuario usuario) {
		
		if(!isAdm(usuario.getEmail())) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		if (usuarioDTO.getEtapaList() == null || usuarioDTO.getEtapaList().isEmpty()) {
			return;
		}
		List<Etapa> etapaList = new ArrayList<>();

		for (Long codEtapa : usuarioDTO.getEtapaList()) {
			etapaList.add(etapaService.findById(codEtapa));
		}
		usuario.setEtapaList(etapaList);
	}

	public Usuario delete(Long id, String emailUsuario) {
		
		if(!isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		Usuario usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
		return usuario;
	}
	
	public boolean isAdm(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		if(usuario == null) {
			return false;
		}
		return usuario.getIsAdmin()==null?Boolean.FALSE:usuario.getIsAdmin();
	}

}
