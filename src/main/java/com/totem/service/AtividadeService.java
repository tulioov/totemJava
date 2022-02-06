package com.totem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.dto.AtividadeDTO;
import com.totem.entity.Atividade;
import com.totem.entity.SubAtividade;
import com.totem.repository.AtividadeRepository;

@Service
public class AtividadeService {
	
	
	@Autowired
    private SubAtividadeService subAtividadeService;

	@Autowired
    private AtividadeRepository atividadeRepository;
	

	public List<Atividade> listar() {
		return atividadeRepository.findAll();
	}
	
	public Atividade findById (Long id) {
		return atividadeRepository.findById(id).get();
	}
	
	public Atividade salvar(AtividadeDTO atividadeDTO) {
		
		List<SubAtividade> subAtividadeList = new ArrayList<SubAtividade>();
		Atividade atividade = new Atividade();
		
		for (Long codSubAtividade : atividadeDTO.getSubAtividadeList()) {
			subAtividadeList.add(subAtividadeService.findById(codSubAtividade));
		}
		
		BeanUtils.copyProperties(atividadeDTO, atividade);
		
		atividade.setSubAtividadeList(subAtividadeList);
		
		atividadeRepository.save(atividade);
		return atividade;
	}
	
	public Atividade delete(Long id) {
		Atividade atividade = atividadeRepository.findById(id).get();
		atividadeRepository.delete(atividade);
		return atividade;
	}

}
