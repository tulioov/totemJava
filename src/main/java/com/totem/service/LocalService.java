package com.totem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.LocalDTO;
import com.totem.entity.Atividade;
import com.totem.entity.Local;
import com.totem.exception.CustomErrorException;
import com.totem.repository.LocalRepository;

@Service
public class LocalService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private LocalRepository localRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	public List<Local> listar(String emailUsuario) {

		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		return localRepository.findAll();
	}

	public Local findById(Long id, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return localRepository.findById(id).get();
	}

	public Local salvar(LocalDTO localDTO, String emailUsuario) {

		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		List<Atividade> atividadeList = new ArrayList<>();
		Local local = new Local();

		if (localDTO.getAtividadeList() != null) {

			for (Long codAtividade : localDTO.getAtividadeList()) {
				atividadeList.add(atividadeService.findById(codAtividade, emailUsuario));
			}

		}

		BeanUtils.copyProperties(localDTO, local);
		local.setAtividadeList(atividadeList);
		localRepository.save(local);
		return local;
	}
	public Local delete(Long id, String emailUsuario) {

		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		Local local = localRepository.findById(id).get();
		local.setUsuarioDelete(emailUsuario);
		local.setDtDelete(new Date());
		localRepository.save(local);
		return local;
	}
}
