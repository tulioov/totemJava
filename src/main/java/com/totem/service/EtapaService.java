package com.totem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.EtapaDTO;
import com.totem.entity.Atividade;
import com.totem.entity.Etapa;
import com.totem.exception.CustomErrorException;
import com.totem.repository.EtapaRepository;

@Service
public class EtapaService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	AtividadeService atividadeService;
	
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
	
	public Etapa salvar(EtapaDTO etapaDTO, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		List<Atividade> atividadeList = new ArrayList<>();
		Etapa etapa = new Etapa();
		
		for (Long codAtividade : etapaDTO.getAtividadeList()) {
			atividadeList.add(atividadeService.findById(codAtividade, emailUsuario));
		}
		
		BeanUtils.copyProperties(etapaDTO, etapa);
		etapa.setAtividadeList(atividadeList);
		
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
