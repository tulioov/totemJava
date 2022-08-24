package com.totem.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.BarcoMonitoracaoDTO;
import com.totem.dto.MonitoracaoAvulsaDTO;
import com.totem.entity.Atividade;
import com.totem.entity.Barco;
import com.totem.entity.Local;
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
	LocalService localService;
	
	@Autowired
	FaseService faseService;

	@Autowired
	private MonitoracaoRepository monitoracaoRepository;

	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	public List<Monitoracao> listar(String emailUsuario) {
		return monitoracaoRepository.findAll();
	}

	public Monitoracao findByUsuarioAndStatusEquals(Usuario usuario, String status) {
		return monitoracaoRepository.findByUsuarioAndStatusEqualsAndDtFimAtividadeTotalIsNull(usuario, status);
	}

	public Monitoracao getMonitoracaoTrabalhandoOuPausa(String nfc, String status) {

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(nfc);
		Monitoracao monitoracao = monitoracaoRepository.findByUsuarioAndStatusEqualsAndDtFimAtividadeTotalIsNull(usuario,status);

		if (monitoracao != null) {
			return monitoracao;
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

	public Barco salvarNovaBarcoMonitoracao(BarcoMonitoracaoDTO monitoracaoDTO, String emailUsuario) {

		Barco barco = barcoService.findById(monitoracaoDTO.getIdBarco(), emailUsuario);

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(monitoracaoDTO.getNfcId());
		usuario.setStatus(EnumStatusUsuario.TRABALHANDO.toString());

		Atividade atividade = atividadeService.findById(monitoracaoDTO.getIdAtividade(), emailUsuario);
		Local local = localService.findById(monitoracaoDTO.getIdLocal(),emailUsuario);

		Monitoracao monitoracao = new Monitoracao();
		monitoracao.setIdBarco(barco.getId());
		monitoracao.setDtInicioAtividade(monitoracaoDTO.getDtInicioAtividade()==null?new Date():monitoracaoDTO.getDtInicioAtividade());
		monitoracao.setUsuario(usuario);
		monitoracao.setAtividade(atividade);
		monitoracao.setLocal(local);
		monitoracao.setStatus(EnumStatusMonitoracao.TRABALHANDO.toString());

		List<Monitoracao> lstMonitoracao = barco.getMonitoracao();
		lstMonitoracao.add(monitoracao);
		barco.setMonitoracao(lstMonitoracao);

		this.salvar(monitoracao, emailUsuario);
		barcoService.salvarBarcoMonitor(barco, emailUsuario);
		usuarioService.salvar(usuario);

		return barco;
	}
	
	private Object salvarMonitoracaoPausarFinalizar(Monitoracao monitoracao, String status, Usuario usuario) {
		monitoracao.setDtFimAtividade(new Date());
		monitoracao.setStatus(status);
		monitoracaoRepository.save(monitoracao);

		monitoracao.getUsuario().setStatus(status);
		usuarioService.salvar(usuario);

		return monitoracao;
		
	}

	public Object continuarPausarFinalizar(BarcoMonitoracaoDTO monitoracaoDTO, String emailUsuario) {

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(monitoracaoDTO.getNfcId());
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);
		Monitoracao monitoracao = null;

		for (Monitoracao monitoracaoVarr : lstMonitoracao) {
			if (monitoracaoVarr.getDtFimAtividade() == null || monitoracaoVarr.getDtFimAtividadeTotal() == null
					&& monitoracaoVarr.getUsuario().equals(usuario)) {
				monitoracao = monitoracaoVarr;
				break;
			}
		}
		if (monitoracao == null) {
			return null;
		}

		if (EnumStatusMonitoracao.PAUSA.toString().equals(monitoracaoDTO.getAcao())) {
			return salvarMonitoracaoPausarFinalizar(monitoracao,EnumStatusMonitoracao.PAUSA.toString(), usuario);
			
		}
		if (EnumStatusMonitoracao.FINALIZADO.toString().equals(monitoracaoDTO.getAcao())) {
			
			monitoracao.setDtFimAtividadeTotal(new Date());
			salvarMonitoracaoPausarFinalizar(monitoracao,EnumStatusMonitoracao.FINALIZADO.toString(),usuario);

			Barco barco = barcoService.findById(monitoracao.getIdBarco(), emailUsuario);
			Long horasTrabalhadas = barco.getHrsBarcoTrabalhadas() == null ? 0L : barco.getHrsBarcoTrabalhadas();
			barco.setHrsBarcoTrabalhadas(monitoracao.getTempoTrabalho() + horasTrabalhadas);
			barcoService.salvarBarcoMonitor(barco, emailUsuario);

			return monitoracao;
		}
		
		monitoracaoDTO.setIdAtividade(monitoracao.getAtividade().getId());
		monitoracaoDTO.setIdBarco(monitoracao.getIdBarco());
		monitoracaoDTO.setDtInicioAtividade(monitoracao.getDtInicioAtividade());
		salvarNovaBarcoMonitoracao(monitoracaoDTO, emailUsuario);
		usuario.setStatus(EnumStatusMonitoracao.TRABALHANDO.toString());
		usuarioService.salvar(usuario);

		monitoracao.setStatus(EnumStatusMonitoracao.PAUSA.toString());
		monitoracao.setDtFimAtividade(new Date());
		monitoracao.setDtFimAtividadeTotal(new Date());
		monitoracaoRepository.save(monitoracao);
		
		return monitoracao;

	}

	public Monitoracao salvarMonitoracaoAvulsa(@Valid MonitoracaoAvulsaDTO monitoracaoAvulsaDTO, String emailUsuario) {
		
		
		
		
		Barco barco = barcoService.findById(monitoracaoAvulsaDTO.getIdBarco(), emailUsuario);
		
		Monitoracao monitoracao = new Monitoracao();
		if(monitoracaoAvulsaDTO.getIdMonitoracao() != null) {
			monitoracao.setId(monitoracaoAvulsaDTO.getIdMonitoracao());
		}
		monitoracao.setIdBarco(monitoracaoAvulsaDTO.getIdBarco());
		monitoracao.setAtividade(atividadeService.findById(monitoracaoAvulsaDTO.getIdAtividade(), emailUsuario));
		monitoracao.setUsuario(usuarioService.findById(monitoracaoAvulsaDTO.getIdUsuario()));
		monitoracao.setFase(faseService.findById(monitoracaoAvulsaDTO.getIdFase()));
		monitoracao.setLocal(localService.findById(monitoracaoAvulsaDTO.getIdLocal(), emailUsuario));
		monitoracao.setDtInicioAtividade(monitoracaoAvulsaDTO.getDtInicioAtividade());
		monitoracao.setDtFimAtividadeTotal(monitoracaoAvulsaDTO.getDtFimAtividade());
		monitoracao.setStatus(EnumStatusMonitoracao.AVULSA.toString());
		
		List<Monitoracao> lstMonitoracao = barco.getMonitoracao();
		lstMonitoracao.add(monitoracao);
		barco.setMonitoracao(lstMonitoracao);

		monitoracaoRepository.save(monitoracao);
		
		Long horasTrabalhadas = barco.getHrsBarcoTrabalhadas() == null ? 0L : barco.getHrsBarcoTrabalhadas();
		barco.setHrsBarcoTrabalhadas(monitoracao.getTempoTrabalho() + horasTrabalhadas);
		barcoService.salvarBarcoMonitor(barco, emailUsuario);
		
		return monitoracao;
	}

	public List<Monitoracao> listarHoraAvulsaByBarcoId(String emailUsuario, Long barcoId) {
		return monitoracaoRepository.findByIdBarco(barcoId);
	}

}
