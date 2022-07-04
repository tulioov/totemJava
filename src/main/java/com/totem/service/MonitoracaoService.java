package com.totem.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.BarcoMonitoracaoDTO;
import com.totem.entity.Barco;
import com.totem.entity.Monitoracao;
import com.totem.entity.SubAtividade;
import com.totem.entity.Usuario;
import com.totem.exception.CustomErrorException;
import com.totem.repository.MonitoracaoRepository;

@Service
public class MonitoracaoService {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	BarcoService barcoService;

	@Autowired
	SubAtividadeService subAtividadeService;
	
	@Autowired
	private MonitoracaoRepository monitoracaoRepository;

	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	public List<Monitoracao> listar(String emailUsuario) {

		// if(!usuarioService.isAdm(emailUsuario)) {
		// throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		// }

		return monitoracaoRepository.findAll();
	}

	public Monitoracao isUsuarioTrabalhando(String nfc) {

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(nfc);
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);

		for (Monitoracao monitoracao : lstMonitoracao) {
			if (monitoracao.getDtFimAtividade() == null) {
				return monitoracao;
			}
		}

		return null;
	}
	
	public Monitoracao isUsuarioPausado(String nfc) {

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(nfc);
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);

		for (Monitoracao monitoracao : lstMonitoracao) {
			if (monitoracao.getDtFimAtividadeTotal() == null) {
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

		// if (!usuarioService.isAdm(emailUsuario)) {
		// throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		// }

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
		SubAtividade subAtividade = subAtividadeService.findById(monitoracaoDTO.getIdSubAtividade(), emailUsuario);

		Monitoracao monitoracao = new Monitoracao();
		monitoracao.setIdBarco(barco.getId());
		monitoracao.setDtInicioAtividade(new Date());
		monitoracao.setUsuario(usuario);
		monitoracao.setSubAtividade(subAtividade);
		monitoracao.setStatus("Trabalhando");

		Set<Monitoracao> lstMonitoracao = new HashSet<>();
		lstMonitoracao.add(monitoracao);
		barco.setMonitoracao(lstMonitoracao);

		this.salvar(monitoracao, emailUsuario);
		barcoService.salvarBarcoMonitor(barco, emailUsuario);

		return barco;
	}

	public Object continuarPausarFinalizar(BarcoMonitoracaoDTO monitoracaoDTO, String emailUsuario) {
		
		Usuario usuario = usuarioService.buscarUsuarioPorNFC(monitoracaoDTO.getNfcId());
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);
		Monitoracao monitoracao = null;
		
		for (Monitoracao monitoracaoVarr : lstMonitoracao) {
			if (monitoracaoVarr.getDtFimAtividade() == null || monitoracaoVarr.getDtFimAtividadeTotal() == null) {
				monitoracao = monitoracaoVarr;
				break;
			}
		}
		if(monitoracao == null) {
			return null;
		}
		
		if("pausar".equals(monitoracaoDTO.getAcao())){
			monitoracao.setDtFimAtividade(new Date());
			monitoracao.setStatus("Pausa");
			monitoracaoRepository.save(monitoracao);
			return monitoracao;
		}
		if("finalizar".equals(monitoracaoDTO.getAcao())){
			monitoracao.setDtFimAtividade(new Date());
			monitoracao.setDtFimAtividadeTotal(new Date());
			monitoracao.setStatus("Finalizado");
			monitoracaoRepository.save(monitoracao);
			Barco barco = barcoService.findById(monitoracao.getIdBarco(), emailUsuario);
			barco.setHrsBarcoTrabalhadas(monitoracao.getTempoTrabalho());
			barcoService.salvarBarcoMonitor(barco, emailUsuario);
			return monitoracao;
		}
		
		Barco barco = barcoService.findById(monitoracao.getIdBarco(), emailUsuario);
		
		Monitoracao monitoracaoNova = new Monitoracao();
		monitoracaoNova.setDtInicioAtividade(monitoracao.getDtInicioAtividade());
		monitoracaoNova.setUsuario(monitoracao.getUsuario());
		monitoracaoNova.setSubAtividade(monitoracao.getSubAtividade());
		monitoracaoNova.setStatus("Trabalhando");
		monitoracaoRepository.save(monitoracaoNova);
		
		Set<Monitoracao> monitor = barco.getMonitoracao();
		monitor.add(monitoracaoNova);
		barcoService.salvarBarcoMonitor(barco, emailUsuario);
		
		monitoracao.setDtFimAtividade(new Date());
		monitoracao.setDtFimAtividadeTotal(new Date());
		monitoracaoRepository.save(monitoracao);
		
		return monitoracao;
		
	}
}
