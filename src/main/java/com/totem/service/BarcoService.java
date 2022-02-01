package com.totem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.entity.Barco;
import com.totem.repository.BarcoRepository;

@Service
public class BarcoService {

	@Autowired
    private BarcoRepository barcoRepository;
	

	public List<Barco> listar() {
		return barcoRepository.findAll();
	}
	
	public Barco findById (Long id) {
		return barcoRepository.findById(id).get();
	}
	
	public Barco salvar(Barco barco) {
		
		barcoRepository.save(barco);
		return barco;
	}
	
	public Barco delete(Long id) {
		Barco barco = barcoRepository.findById(id).get();
		barcoRepository.delete(barco);
		return barco;
	}

}
