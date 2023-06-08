package com.totem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.BarcoTemplateDTO;
import com.totem.entity.Atividade;
import com.totem.entity.BarcoTemplate;
import com.totem.entity.BarcoTemplate;
import com.totem.entity.Usuario;
import com.totem.exception.CustomErrorException;
import com.totem.repository.BarcoTemplateRepository;

@Service
public class BarcoTemplateService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	AtividadeService atividadeService;
	
	@Autowired
    private BarcoTemplateRepository barcoTemplateRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<BarcoTemplate> listar(String emailUsuario) {
		return barcoTemplateRepository.findAll();
	}
	
	public BarcoTemplate findById (Long id) {
		return barcoTemplateRepository.findById(id).get();
	}
	
	public BarcoTemplate salvar(BarcoTemplateDTO barcoTemplateDTO, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		List<Atividade> atividadeList = new ArrayList<>();
		BarcoTemplate barcoTemplate = new BarcoTemplate();
		
		for (Long codAtividade : barcoTemplateDTO.getAtividadeList()) {
			atividadeList.add(atividadeService.findById(codAtividade, emailUsuario));
		}
		
		BeanUtils.copyProperties(barcoTemplateDTO, barcoTemplate);
		barcoTemplate.setAtividadeList(atividadeList);
		
		barcoTemplateRepository.save(barcoTemplate);
		return barcoTemplate;
	}
	
	public BarcoTemplate delete(Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		BarcoTemplate barcoTemplate = barcoTemplateRepository.findById(id).get();
		barcoTemplate.setUsuarioDelete(emailUsuario);
		barcoTemplate.setDtDelete(new Date());
		barcoTemplateRepository.save(barcoTemplate);
		return barcoTemplate;
	}


}
