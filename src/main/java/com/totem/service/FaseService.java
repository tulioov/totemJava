package com.totem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.FaseDTO;
import com.totem.entity.Fase;
import com.totem.entity.Local;
import com.totem.exception.CustomErrorException;
import com.totem.repository.FaseRepository;

@Service
public class FaseService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	LocalService localService;
	
	@Autowired
    private FaseRepository faseRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<Fase> listar(String emailUsuario) {
		return faseRepository.findAll();
	}
	
	public Fase findById (Long id) {
		return faseRepository.findById(id).get();
	}
	
	public Fase salvar(FaseDTO faseDTO, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		List<Local> localList = new ArrayList<>();
		Fase fase = new Fase();
		
		for (Long codLocal : faseDTO.getLocalList()) {
			localList.add(localService.findById(codLocal, emailUsuario));
		}
		
		BeanUtils.copyProperties(faseDTO, fase);
		fase.setLocalList(localList);
		
		faseRepository.save(fase);
		return fase;
	}
	
	public Fase delete(Long id, String emailUsuario) {
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		Fase fase = faseRepository.findById(id).get();
		fase.setUsuarioDelete(emailUsuario);
		fase.setDtDelete(new Date());
		faseRepository.save(fase);
		return fase;
	}

}
