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
import com.totem.entity.MonitoracaoAtividade;
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
	MonitoracaoAtividadeService monitoracaoAtividadeService;

	@Autowired
	private MonitoracaoRepository monitoracaoRepository;

	private static final String ERRO_PERMISSAO = "Usuário sem permissão";

	public List<Monitoracao> listar(String emailUsuario) {

		// if(!usuarioService.isAdm(emailUsuario)) {
		// throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ERRO_PERMISSAO);
		// }

		return monitoracaoRepository.findAll();
	}

	public boolean isUsuarioTrabalhando(String nfc) {

		Usuario usuario = usuarioService.buscarUsuarioPorNFC(nfc);
		List<Monitoracao> lstMonitoracao = monitoracaoRepository.findByUsuario(usuario);

		for (Monitoracao monitoracao : lstMonitoracao) {
			if (monitoracao.getMonitoracaoAtividade() != null) {
				for (MonitoracaoAtividade monitoracaoAtividade : monitoracao.getMonitoracaoAtividade()) {
					if (monitoracaoAtividade.getDtFimAtividade() == null) {
						return true;
					}
				}
			}
		}

		return false;
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

		MonitoracaoAtividade monitoracaoAtividade = new MonitoracaoAtividade();
		Set<MonitoracaoAtividade> lstMonitoracaoAtividade = new HashSet<>();
		lstMonitoracaoAtividade.add(monitoracaoAtividade);
		monitoracaoAtividade.setDtInicioAtividade(new Date());
		monitoracaoAtividadeService.salvar(monitoracaoAtividade, emailUsuario);

		Monitoracao monitoracao = new Monitoracao();
		monitoracao.setUsuario(usuario);
		monitoracao.setSubAtividade(subAtividade);
		monitoracao.setMonitoracaoAtividade(lstMonitoracaoAtividade);

		Set<Monitoracao> lstMonitoracao = new HashSet<>();
		lstMonitoracao.add(monitoracao);

		barco.setMonitoracao(lstMonitoracao);

		this.salvar(monitoracao, emailUsuario);
		barcoService.salvar(barco, emailUsuario);

		return barco;
	}
}
