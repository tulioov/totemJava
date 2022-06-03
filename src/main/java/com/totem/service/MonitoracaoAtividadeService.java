package com.totem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.entity.MonitoracaoAtividade;
import com.totem.repository.MonitoracaoAtividadeRepository;

@Service
public class MonitoracaoAtividadeService {

	@Autowired
	private MonitoracaoAtividadeRepository monitoracaoAtividadeRepository;

	public MonitoracaoAtividade salvar(MonitoracaoAtividade monitoracaoAtividade, String emailUsuario) {

		return monitoracaoAtividadeRepository.save(monitoracaoAtividade);
	}
}
