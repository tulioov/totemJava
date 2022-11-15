package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Barco;
import com.totem.entity.Monitoracao;

@Repository
public interface BarcoRepository extends CrudRepository<Barco, Long> {

	List<Barco> findAll();
	
	List<Barco> findByDtDeleteIsNull();
	
	Barco findByMonitoracao(Monitoracao monitoracao);

}
