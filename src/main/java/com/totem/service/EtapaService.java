package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.entity.Etapa;
import com.totem.repository.EtapaRepository;

@Service
public class EtapaService {

	@Autowired
    private EtapaRepository etapaRepository;
	

	public List<Etapa> listar() {
		return etapaRepository.findAll();
	}
	
	public Etapa findById (Long id) {
		return etapaRepository.findById(id).get();
	}
	
	public Etapa salvar(Etapa etapa) {
		
		etapaRepository.save(etapa);
		return etapa;
	}
	
	public Etapa delete(Long id) {
		Etapa etapa = etapaRepository.findById(id).get();
		etapaRepository.delete(etapa);
		return etapa;
	}

}
