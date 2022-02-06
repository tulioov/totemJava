package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.entity.SubAtividade;
import com.totem.repository.SubAtividadeRepository;

@Service
public class SubAtividadeService {

	@Autowired
    private SubAtividadeRepository subAtividadeRepository;
	

	public List<SubAtividade> listar() {
		return subAtividadeRepository.findAll();
	}
	
	public SubAtividade findById (Long id) {
		return subAtividadeRepository.findById(id).get();
	}
	
	public SubAtividade salvar(SubAtividade subAtividade) {
		
		subAtividadeRepository.save(subAtividade);
		return subAtividade;
	}
	
	public SubAtividade delete(Long id) {
		SubAtividade subAtividade = subAtividadeRepository.findById(id).get();
		subAtividadeRepository.delete(subAtividade);
		return subAtividade;
	}

}
