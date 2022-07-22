package com.totem.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.BarcoMonitoracaoDTO;
import com.totem.entity.Atividade;
import com.totem.entity.Barco;
import com.totem.entity.Monitoracao;
import com.totem.entity.Usuario;
import com.totem.enums.EnumStatusMonitoracao;
import com.totem.enums.EnumStatusUsuario;
import com.totem.exception.CustomErrorException;
import com.totem.repository.MonitoracaoRepository;

@Service
public class MonitoracaoService {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	BarcoService barcoService;

	@Autowired
	AtividadeService atividadeService;
	
	@Autowired
	private MonitoracaoRepository monitoracaoRepository;

	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	public List<Monitoracao> listar(String emailUsuario) {
		return monitoracaoRepository.findAll();
	}

	public Monitoracao isUsuarioTrabalhando(String nfc) {

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(nfc);
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);

		for (Monitoracao monitoracao : lstMonitoracao) {
			if (EnumStatusMonitoracao.TRABALHANDO.toString().equals(monitoracao.getStatus())) {
				return monitoracao;
			}
		}

		return null;
	}
	
	public Monitoracao isUsuarioPausado(String nfc) {

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(nfc);
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);

		for (Monitoracao monitoracao : lstMonitoracao) {
			if (EnumStatusMonitoracao.PAUSA.toString().equals(monitoracao.getStatus())) {
				return monitoracao;
			}
		}

		return null;
	}

	public Monitoracao findById(Long id, String emailUsuario) {
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		return monitoracaoRepository.findById(id).get();
	}

	public Monitoracao salvar(Monitoracao monitoracao, String emailUsuario) {
		monitoracaoRepository.save(monitoracao);
		return monitoracao;
	}

	public Monitoracao delete(Long id, String emailUsuario) {

		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}

		Monitoracao monitoracao = monitoracaoRepository.findById(id).get();
		monitoracaoRepository.delete(monitoracao);
		return monitoracao;
	}

	public Barco salvarBarcoMonitoracao(BarcoMonitoracaoDTO monitoracaoDTO, String emailUsuario) {

		Barco barco = barcoService.findById(monitoracaoDTO.getIdBarco(), emailUsuario);
		
		Usuario usuario = usuarioService.buscarUsuarioPorNFC(monitoracaoDTO.getNfcId());
		usuario.setStatus(EnumStatusUsuario.TRABALHANDO.toString());
		
		Atividade atividade = atividadeService.findById(monitoracaoDTO.getIdAtividade(), emailUsuario);

		Monitoracao monitoracao = new Monitoracao();
		monitoracao.setIdBarco(barco.getId());
		monitoracao.setDtInicioAtividade(new Date());
		monitoracao.setUsuario(usuario);
		monitoracao.setAtividade(atividade);
		monitoracao.setStatus(EnumStatusMonitoracao.TRABALHANDO.toString());

		Set<Monitoracao> lstMonitoracao = barco.getMonitoracao();
		lstMonitoracao.add(monitoracao);
		barco.setMonitoracao(lstMonitoracao);

		this.salvar(monitoracao, emailUsuario);
		barcoService.salvarBarcoMonitor(barco, emailUsuario);
		usuarioService.salvar(usuario);

		return barco;
	}

	public Object continuarPausarFinalizar(BarcoMonitoracaoDTO monitoracaoDTO, String emailUsuario) {
		
		Usuario usuario = usuarioService.buscarUsuarioPorNFC(monitoracaoDTO.getNfcId());
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);
		Monitoracao monitoracao = null;
		
		for (Monitoracao monitoracaoVarr : lstMonitoracao) {
			if (monitoracaoVarr.getDtFimAtividade() == null || monitoracaoVarr.getDtFimAtividadeTotal() == null && monitoracaoVarr.getUsuario().equals(usuario)) {
				monitoracao = monitoracaoVarr;
				break;
			}
		}
		if(monitoracao == null) {
			return null;
		}
		
		if(EnumStatusMonitoracao.PAUSA.toString().equals(monitoracaoDTO.getAcao())){
			monitoracao.setDtFimAtividade(new Date());
			monitoracao.setStatus(EnumStatusMonitoracao.PAUSA.toString());
			monitoracaoRepository.save(monitoracao);
			
			usuario.setStatus(EnumStatusMonitoracao.PAUSA.toString());
			usuarioService.salvar(usuario);
			
			Barco barco = barcoService.findById(monitoracao.getIdBarco(), emailUsuario);
			Set<Monitoracao> monitor = barco.getMonitoracao();
			monitor.add(monitoracao);
			barco.setMonitoracao(monitor);
			barcoService.salvar(barco, emailUsuario);
			
			return monitoracao;
		}
		if(EnumStatusMonitoracao.FINALIZADO.toString().equals(monitoracaoDTO.getAcao())){
			monitoracao.setDtFimAtividade(new Date());
			monitoracao.setDtFimAtividadeTotal(new Date());
			monitoracao.setStatus(EnumStatusMonitoracao.FINALIZADO.toString());
			monitoracaoRepository.save(monitoracao);
			
			usuario.setStatus(EnumStatusMonitoracao.FINALIZADO.toString());
			usuarioService.salvar(usuario);
			
			Barco barco = barcoService.findById(monitoracao.getIdBarco(), emailUsuario);
			Long horasTrabalhadas = barco.getHrsBarcoTrabalhadas()==null?0L:barco.getHrsBarcoTrabalhadas();
			barco.setHrsBarcoTrabalhadas(monitoracao.getTempoTrabalho()+horasTrabalhadas);
			barcoService.salvarBarcoMonitor(barco, emailUsuario);
			
			return monitoracao;
		}
		
		monitoracao.setStatus(EnumStatusMonitoracao.TRABALHANDO.toString());
		monitoracaoRepository.save(monitoracao);
		usuario.setStatus(EnumStatusMonitoracao.TRABALHANDO.toString());
		usuarioService.salvar(usuario);
		
		
		return monitoracao;
		
	}
}
