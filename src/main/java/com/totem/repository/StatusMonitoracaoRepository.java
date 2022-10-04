package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.StatusMonitoracao;

@Repository
public interface StatusMonitoracaoRepository extends CrudRepository<StatusMonitoracao, Long> {

	List<StatusMonitoracao> findAll();
	
	
	StatusMonitoracao findByConstanteCampo(String constanteCampo);
}
