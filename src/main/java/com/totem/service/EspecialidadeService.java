package com.totem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.EspecialidadeDTO;
import com.totem.entity.Atividade;
import com.totem.entity.Especialidade;
import com.totem.entity.Fase;
import com.totem.exception.CustomErrorException;
import com.totem.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private FaseService faseService;
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	public List<Especialidade> listar(String emailUsuario) {

		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		return especialidadeRepository.findAll();
	}

	public Especialidade findById(Long id, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return especialidadeRepository.findById(id).get();
	}

	public Especialidade salvar(EspecialidadeDTO especialidadeDTO, String emailUsuario) {

		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		List<Atividade> atividadeList = new ArrayList<>();
		Especialidade especialidade = new Especialidade();

		if (especialidadeDTO.getAtividadeList() != null) {

			for (Long codAtividade : especialidadeDTO.getAtividadeList()) {
				atividadeList.add(atividadeService.findById(codAtividade, emailUsuario));
			}

		}

		BeanUtils.copyProperties(especialidadeDTO, especialidade);
		especialidade.setAtividadeList(atividadeList);
		especialidadeRepository.save(especialidade);
		return especialidade;
	}
	public Especialidade delete(Long id, String emailUsuario) {

		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		Especialidade especialidade = especialidadeRepository.findById(id).get();
		especialidade.setUsuarioDelete(emailUsuario);
		especialidade.setDtDelete(new Date());
		especialidadeRepository.save(especialidade);
		return especialidade;
	}
	

	public void salvar(Especialidade especialidade, String emailUsuario) {
		especialidadeRepository.save(especialidade);
	}
}
