package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.entity.SubAtividade;
import com.totem.exception.CustomErrorException;
import com.totem.repository.SubAtividadeRepository;

@Service
public class SubAtividadeService {
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private SubAtividadeRepository subAtividadeRepository;

	public List<SubAtividade> listar(String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return subAtividadeRepository.findAll();
	}

	public SubAtividade findById(Long id, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return subAtividadeRepository.findById(id).get();
	}

	public SubAtividade salvar(SubAtividade subAtividade, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		subAtividadeRepository.save(subAtividade);
		return subAtividade;
	}

	public SubAtividade delete(Long id, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		SubAtividade subAtividade = subAtividadeRepository.findById(id).get();
		subAtividadeRepository.delete(subAtividade);
		return subAtividade;
	}

}
