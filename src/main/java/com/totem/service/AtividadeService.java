package com.totem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.entity.Atividade;
import com.totem.entity.Local;
import com.totem.exception.CustomErrorException;
import com.totem.repository.AtividadeRepository;

@Service
public class AtividadeService {
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	LocalService localService;

	@Autowired
	private AtividadeRepository atividadeRepository;

	public List<Atividade> listar(String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return atividadeRepository.findByUsuarioDeleteIsNull();
	}

	public Atividade findById(Long id, String emailUsuario) {
//		if (!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
//		}
		return atividadeRepository.findById(id).get();
	}

	public Atividade salvar(Atividade atividade, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		atividadeRepository.save(atividade);
		return atividade;
	}

	public Atividade delete(Long id, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		Atividade atividade = atividadeRepository.findById(id).get();
		atividade.setUsuarioDelete(emailUsuario);
		atividade.setDtDelete(new Date());
		atividadeRepository.save(atividade);
		return atividade;
	}
	
	
	public List<Atividade> listarAtividadeByLocalId(String emailUsuario, Long id) {
		Local local = localService.findById(id,emailUsuario);
		return local.getAtividadeList();
	}

}
