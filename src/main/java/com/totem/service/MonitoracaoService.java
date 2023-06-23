package com.totem.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.totem.dto.BarcoMonitoracaoDTO;
import com.totem.dto.FiltroPesquisaMonitoracaoDTO;
import com.totem.dto.MonitoracaoAvulsaDTO;
import com.totem.entity.Atividade;
import com.totem.entity.Barco;
import com.totem.entity.Local;
import com.totem.entity.Monitoracao;
import com.totem.entity.StatusMonitoracao;
import com.totem.entity.Usuario;
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
	StatusMonitoracaoService statusMonitoracaoService;

	@Autowired
	private MonitoracaoRepository monitoracaoRepository;

	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	public List<Monitoracao> listar(String emailUsuario) {
		return monitoracaoRepository.findAll();
	}

	public Monitoracao findByUsuarioAndStatusEquals(Usuario usuario, String status) {
		return monitoracaoRepository
				.findByUsuarioAndStatusMonitoracaoConstanteCampoContainingAndDtFimAtividadeIsNull(usuario, status);
	}

	public Monitoracao getMonitoracaoTrabalhandoOuPausa(Usuario usuario, String status) {

		Monitoracao monitoracao = monitoracaoRepository
				.findByUsuarioAndStatusMonitoracaoConstanteCampoContainingAndDtFimAtividadeIsNull(usuario, status);

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
		
		Usuario usuario = null;

		Barco barco = barcoService.findById(monitoracaoDTO.getIdBarco(), emailUsuario);

		if(!monitoracaoDTO.getNfcId().equals("")){
			usuario = usuarioService.buscarUsuarioPorNFC(monitoracaoDTO.getNfcId());
		}else {
			usuario = usuarioService.findById(monitoracaoDTO.getIdUsuario());
		}
		
		usuario.setStatus(EnumStatusUsuario.TRABALHANDO.toString());

		Atividade atividade = atividadeService.findById(monitoracaoDTO.getIdAtividade(), emailUsuario);
		Local local = localService.findById(monitoracaoDTO.getIdLocal(), emailUsuario);
		StatusMonitoracao statusMonitoracao = statusMonitoracaoService
				.findByConstanteCampo(EnumStatusUsuario.TRABALHANDO.toString());

		Monitoracao monitoracao = new Monitoracao();
		monitoracao.setIdBarco(barco.getId());
		monitoracao.setDtInicioAtividade(
				monitoracaoDTO.getDtInicioAtividade() == null ? new Date() : monitoracaoDTO.getDtInicioAtividade());
		monitoracao.setUsuario(usuario);
		monitoracao.setAtividade(atividade);
		monitoracao.setLocal(local);
		monitoracao.setStatusMonitoracao(statusMonitoracao);

		List<Monitoracao> lstMonitoracao = barco.getMonitoracao();
		lstMonitoracao.add(monitoracao);
		barco.setMonitoracao(lstMonitoracao);

		this.salvar(monitoracao, emailUsuario);
		barcoService.salvarBarcoMonitor(barco, emailUsuario);
		usuarioService.salvar(usuario);

		return barco;
	}

	private Object salvarMonitoracaoPausarFinalizar(Monitoracao monitoracao, String statusMonitoracaoConstante,
			String status, Usuario usuario) {

		StatusMonitoracao statusMonitoracao = statusMonitoracaoService.findByConstanteCampo(statusMonitoracaoConstante);
		Barco barco = barcoService.findById(monitoracao.getIdBarco(), monitoracao.getUsuario().getEmail());

		monitoracao.setDtFimAtividade(new Date());
		if ("FINALIZADO".equals(status)) {
			monitoracao.setStatusMonitoracao(statusMonitoracao);
			monitoracaoRepository.save(monitoracao);
			return monitoracao;
		}
		monitoracaoRepository.save(monitoracao);

		Monitoracao monitoracaoNova = new Monitoracao();
		monitoracaoNova.setUsuario(monitoracao.getUsuario());
		monitoracaoNova.setLocal(monitoracao.getLocal());
		monitoracaoNova.setAtividade(monitoracao.getAtividade());
		monitoracaoNova.setIdBarco(monitoracao.getIdBarco());
		monitoracaoNova.setDtInicioAtividade(new Date());

		monitoracaoNova.setStatusMonitoracao(statusMonitoracao);
		monitoracaoRepository.save(monitoracaoNova);

		List<Monitoracao> lstMonitoracao = barco.getMonitoracao();
		lstMonitoracao.add(monitoracaoNova);
		barco.setMonitoracao(lstMonitoracao);

		barcoService.salvarBarcoMonitor(barco, monitoracao.getUsuario().getEmail());

		monitoracao.getUsuario().setStatus(status);
		usuarioService.salvar(usuario);

		return monitoracao;

	}

	public Object continuarPausarFinalizar(BarcoMonitoracaoDTO monitoracaoDTO, String emailUsuario) {

		Usuario usuario = null;
		
		if(!monitoracaoDTO.getNfcId().equals("")){
			usuario = usuarioService.buscarUsuarioPorNFC(monitoracaoDTO.getNfcId());
		}else {
			usuario = usuarioService.findById(monitoracaoDTO.getIdUsuario());
		}
		
		Monitoracao monitoracao = monitoracaoRepository.findByUsuarioAndDtFimAtividadeIsNull(usuario);

		if (monitoracao == null) {
			return null;
		}

		if (monitoracaoDTO.getAcao().contains("PAUSA")) {
			return salvarMonitoracaoPausarFinalizar(monitoracao, monitoracaoDTO.getAcao(),
					EnumStatusUsuario.PAUSA.toString(), usuario);
		}
		if ("FINALIZADO".equals(monitoracaoDTO.getAcao())) {

			monitoracao.setDtFimAtividade(new Date());
			salvarMonitoracaoPausarFinalizar(monitoracao, monitoracaoDTO.getAcao(), "FINALIZADO", usuario);

			Barco barco = barcoService.findById(monitoracao.getIdBarco(), emailUsuario);
			Long horasTrabalhadas = barco.getHrsBarcoTrabalhadas() == null ? 0L : barco.getHrsBarcoTrabalhadas();
			barco.setHrsBarcoTrabalhadas(monitoracao.getTempoTrabalho() + horasTrabalhadas);
			barcoService.salvarBarcoMonitor(barco, emailUsuario);

			usuario.setStatus(null);
			usuarioService.salvar(usuario);

			return monitoracao;
		}

		// StatusMonitoracao statusMonitoracao =
		// statusMonitoracaoService.findByConstanteCampo(EnumStatusUsuario.TRABALHANDO.toString());

		monitoracaoDTO.setIdAtividade(monitoracao.getAtividade().getId());
		monitoracaoDTO.setIdLocal(monitoracao.getLocal().getId());
		monitoracaoDTO.setIdBarco(monitoracao.getIdBarco());
		monitoracaoDTO.setDtInicioAtividade(monitoracao.getDtInicioAtividade());
		salvarNovaBarcoMonitoracao(monitoracaoDTO, emailUsuario);

		usuario.setStatus(EnumStatusUsuario.TRABALHANDO.toString());
		usuarioService.salvar(usuario);

		// monitoracao.setStatusMonitoracao(statusMonitoracao);
		monitoracao.setDtFimAtividade(new Date());
		monitoracaoRepository.save(monitoracao);

		return monitoracao;

	}

	public Monitoracao salvarMonitoracaoAvulsa(@Valid MonitoracaoAvulsaDTO monitoracaoAvulsaDTO, String emailUsuario) {

		Barco barco = barcoService.findById(monitoracaoAvulsaDTO.getIdBarco(), emailUsuario);
		StatusMonitoracao statusMonitoracao = statusMonitoracaoService.findByConstanteCampo("AVULSA");

		Monitoracao monitoracao = new Monitoracao();
		if (monitoracaoAvulsaDTO.getIdMonitoracao() != null) {
			monitoracao.setId(monitoracaoAvulsaDTO.getIdMonitoracao());
		}
		monitoracao.setIdBarco(monitoracaoAvulsaDTO.getIdBarco());
		monitoracao.setAtividade(atividadeService.findById(monitoracaoAvulsaDTO.getIdAtividade(), emailUsuario));
		monitoracao.setUsuario(usuarioService.findById(monitoracaoAvulsaDTO.getIdUsuario()));
		monitoracao.setFase(faseService.findById(monitoracaoAvulsaDTO.getIdFase()));
		monitoracao.setLocal(localService.findById(monitoracaoAvulsaDTO.getIdLocal(), emailUsuario));
		monitoracao.setDtInicioAtividade(monitoracaoAvulsaDTO.getDtInicioAtividade());
		monitoracao.setDtFimAtividade(monitoracaoAvulsaDTO.getDtFimAtividade());
		monitoracao.setStatusMonitoracao(statusMonitoracao);

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

	public List<Monitoracao> listarMonitoracaoByFiltro(String emailUsuario, FiltroPesquisaMonitoracaoDTO filtroPesquisaMonitoracaoDTO) {
		
		if (!usuarioService.isAdm(emailUsuario)) {
			throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		}
		
		boolean isData = filtroPesquisaMonitoracaoDTO.getDataInidio() != null && filtroPesquisaMonitoracaoDTO.getDataFim() != null;
		boolean isUsuario = !filtroPesquisaMonitoracaoDTO.getUsuarioLstId().isEmpty();
		
		if(!isData && isUsuario) {
			return monitoracaoRepository.findByUsuarioIdIn(filtroPesquisaMonitoracaoDTO.getUsuarioLstId());
		}
		
		if(isData && !isUsuario) {
			return monitoracaoRepository.findBydtInicioAtividadeBetween(filtroPesquisaMonitoracaoDTO.getDataInidio(),filtroPesquisaMonitoracaoDTO.getDataFim());
		}
		
		return null;
	}

}
