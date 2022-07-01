package com.totem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Feriado;

@Repository
public interface FeriadoRepository extends CrudRepository<Feriado, Long> {

	List<Feriado> findAll();
	
	List<Feriado> findByDtFeriadoBetween(Date dataInicio, Date dataFim);
}
