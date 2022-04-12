package com.totem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.AtividadeDTO;
import com.totem.entity.Atividade;
import com.totem.entity.SubAtividade;
import com.totem.exception.CustomErrorException;
import com.totem.repository.AtividadeRepository;

@Service
public class AtividadeService {
	
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
    private SubAtividadeService subAtividadeService;

	@Autowired
    private AtividadeRepository atividadeRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<Atividade> listar(String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		return atividadeRepository.findAll();
	}
	
	public Atividade findById (Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return atividadeRepository.findById(id).get();
	}
	
	public Atividade salvar(AtividadeDTO atividadeDTO, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		List<SubAtividade> subAtividadeList = new ArrayList<>();
		Atividade atividade = new Atividade();
		
		for (Long codSubAtividade : atividadeDTO.getSubAtividadeList()) {
			subAtividadeList.add(subAtividadeService.findById(codSubAtividade, emailUsuario));
		}
		
		BeanUtils.copyProperties(atividadeDTO, atividade);
		
		atividade.setSubAtividadeList(subAtividadeList);
		
		atividadeRepository.save(atividade);
		return atividade;
	}
	
	public Atividade delete(Long id, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		Atividade atividade = atividadeRepository.findById(id).get();
		atividadeRepository.delete(atividade);
		return atividade;
	}

}
