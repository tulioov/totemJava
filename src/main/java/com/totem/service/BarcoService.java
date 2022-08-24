package com.totem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totem.dto.BarcoDTO;
import com.totem.dto.MonitorPausa;
import com.totem.entity.Barco;
import com.totem.entity.Feriado;
import com.totem.entity.Monitoracao;
import com.totem.enums.EnumStatusMonitoracao;
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
	private FeriadoService feriadoService;
	
	@Autowired
    private BarcoRepository barcoRepository;
	
	private static final String ERRO_PERMISSAO = "Usuário sem permissão";  
	

	public List<Barco> listar(String emailUsuario) {
		return barcoRepository.findAll();
	}
	
	public Object listar(String emailUsuario, String nfc) {
		
		
		if(usuarioService.findBycodRfid(nfc) == null) {
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Usuário não encontrado. Favor atualizar a tela ou chamar o suporte.");
		}
		
		Monitoracao monitoracao = null;
		
		monitoracao = monitoracaoService.getMonitoracaoTrabalhandoOuPausa(nfc,EnumStatusMonitoracao.TRABALHANDO.toString());
		if(monitoracao == null) {
			monitoracao = monitoracaoService.getMonitoracaoTrabalhandoOuPausa(nfc,EnumStatusMonitoracao.PAUSA.toString());
		}
		
		if(monitoracao != null){
			Barco barco =  barcoRepository.findByMonitoracao(monitoracao);
			MonitorPausa monitorPausa = new MonitorPausa();
			monitorPausa.setBarco(barco);
			monitorPausa.setMonitoracao(monitoracao);
			monitorPausa.setStatus(monitoracao.getStatus());
			return monitorPausa;
		}
		
		return barcoRepository.findAll();
	}
	
	public Barco findById (Long id, String emailUsuario) {
		return barcoRepository.findById(id).get();
	}
	
	@Transactional
	public Barco salvar(Barco barco, String emailUsuario) {
		
		barcoRepository.save(barco);
		return barco;
	}
	
	@Transactional
	public Barco salvar(BarcoDTO barcoDTO, String emailUsuario) {
		
		Barco barco = new Barco();
		
		if(barcoDTO.getId() != null) {
			Optional<Barco> barcoOp = barcoRepository.findById(barcoDTO.getId());
			if(barcoOp.isPresent()) {
				barco = barcoOp.get();
			}
		}
		
		if(barco.getMonitoracao() != null) {
			List<Monitoracao> monitor = barco.getMonitoracao();
			if(barcoDTO.getMonitoracao() == null) {
				barco.setMonitoracao(monitor);
			}
		}
		BeanUtils.copyProperties(barcoDTO, barco);
		barcoRepository.save(barco);
		
		return barco;
	}
	
	
	@Transactional
	public Barco salvarBarcoMonitor(Barco barco, String emailUsuario) {
		
		UtilDate utilDate = new UtilDate();
		
		if(barco.getDtInicio() == null) {
			barco.setDtInicio(new Date());
			barco.setDtFim(utilDate.somarDiasData(barco.getDtInicio(), barco.getTempoDiasFabricao().intValue()));
		}
		
		List<Feriado> lstFeriado = feriadoService.findByDtFeriadoBetween(barco.getDtInicio(), barco.getDtFim());
		
		if(barco.getDtFim() == null || barco.getHrsBarcoAutomatizada() == null) {
			barco.setDtFim(utilDate.somarDiasData(barco.getDtInicio(), barco.getTempoDiasFabricao().intValue()));
			Long diasPrevistos = utilDate.getDiasUteisPrevistos(barco.getDtInicio(), barco.getDtFim(), lstFeriado);
			barco.setHrsBarcoAutomatizada(diasPrevistos*8);
		}
		
		barcoRepository.save(barco);
		
		return barco;
	}
	
	public Barco delete(Long id, String emailUsuario) {
		
		if(!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		Barco barco = barcoRepository.findById(id).get();
		
		barco.setUsuarioDelete(emailUsuario);
		barco.setDtDelete(new Date());
		barco.setStatus("INATIVO");
		barcoRepository.save(barco);
		return barco;
	}

}
