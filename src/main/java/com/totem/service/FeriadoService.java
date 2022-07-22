package com.totem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.FeriadoDTO;
import com.totem.entity.Feriado;
import com.totem.exception.CustomErrorException;
import com.totem.repository.FeriadoRepository;

@Service
public class FeriadoService {
	
	@Autowired
    private FeriadoRepository feriadoRepository;
	
	@Autowired
    private UsuarioService usuarioService;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  

	public List<Feriado> listar(String emailUsuario) {
//		if(!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
//		}
		return feriadoRepository.findAll();
	}
	
	public Feriado findById (Long id) {
		return feriadoRepository.findById(id).get();
	}
	
	public List<Feriado> findByDtFeriadoBetween(Date dataInicio, Date dataFim){
		return feriadoRepository.findByDtFeriadoBetween(dataInicio,dataFim);
	}
	
	public Feriado salvar(FeriadoDTO feriadoDTO, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		Feriado feriado = new Feriado();
		BeanUtils.copyProperties(feriadoDTO, feriado);
		feriadoRepository.save(feriado);
		return feriado;
	}
	
	public Feriado delete(Long id, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		Feriado feriado = feriadoRepository.findById(id).get();
		feriado.setUsuarioDelete(emailUsuario);
		feriado.setDtDelete(new Date());
		feriadoRepository.save(feriado);
		return feriado;
	}

}
