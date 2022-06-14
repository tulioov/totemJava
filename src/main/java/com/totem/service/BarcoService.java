package com.totem.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totem.dto.BarcoDTO;
import com.totem.dto.MonitorPausa;
import com.totem.entity.Barco;
import com.totem.entity.Monitoracao;
import com.totem.exception.CustomErrorException;
import com.totem.repository.BarcoRepository;
import com.totem.util.UtilDate;

@Service
public class BarcoService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	MonitoracaoService monitoracaoService;
	
	@Autowired
    private BarcoRepository barcoRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<Barco> listar(String emailUsuario) {
//		if(!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
//		}
		return barcoRepository.findAll();
	}
	
	public Object listar(String emailUsuario, String nfc) {
//		if(!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
//		}
		
		if(monitoracaoService.isUsuarioTrabalhando(nfc) != null){
			Monitoracao monitoracao = monitoracaoService.isUsuarioTrabalhando(nfc);
			Barco barco =  barcoRepository.findByMonitoracao(monitoracao);
			MonitorPausa monitorPausa = new MonitorPausa();
			monitorPausa.setBarco(barco);
			monitorPausa.setMonitoracao(monitoracao);
			monitorPausa.setStatus("Trabalhando");
			return monitorPausa;
		}
		
		if(monitoracaoService.isUsuarioPausado(nfc) != null){
			Monitoracao monitoracao = monitoracaoService.isUsuarioPausado(nfc);
			Barco barco =  barcoRepository.findByMonitoracao(monitoracao);
			MonitorPausa monitorPausa = new MonitorPausa();
			monitorPausa.setBarco(barco);
			monitorPausa.setMonitoracao(monitoracao);
			monitorPausa.setStatus("Pausa");
			return monitorPausa;
		}
		
		return barcoRepository.findAll();
	}
	
	public Barco findById (Long id, String emailUsuario) {
//		if(!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
//		}
		return barcoRepository.findById(id).get();
	}
	
	@Transactional
	public Barco salvar(BarcoDTO barcoDTO, String emailUsuario) {
		
//		if(!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED,ERRO_PERMISSAO);
//		}
		
		Barco barco = barcoRepository.findById(barcoDTO.getId()).get();
		Set<Monitoracao> monitor = barco.getMonitoracao();
		BeanUtils.copyProperties(barcoDTO, barco);
		if(barcoDTO.getMonitoracao() == null) {
			barco.setMonitoracao(monitor);
		}
		
		barcoRepository.save(barco);
		
		return barco;
	}
	
	
	@Transactional
	public Barco salvarBarcoMonitor(Barco barco, String emailUsuario) {
		
//		if(!usuarioService.isAdm(emailUsuario)) {
//			throw new CustomErrorException(HttpStatus.UNAUTHORIZED,ERRO_PERMISSAO);
//		}
		
		barco.setDtFim(UtilDate.somarDiasData(barco.getDtInicio(), barco.getTempoDiasFabricao().intValue()));
		barcoRepository.save(barco);
		
		return barco;
	}
	
	public Barco delete(Long id, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		Barco barco = barcoRepository.findById(id).get();
//		Monitoracao monitoracao = monitoracaoService.findByBarco(barco.getId(), emailUsuario);
		
//		if(monitoracao != null) {
//			monitoracaoService.delete(id, emailUsuario);
//		}
		
		barcoRepository.delete(barco);
		return barco;
	}

}
