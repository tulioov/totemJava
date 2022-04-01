package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Atividade;

@Repository
public interface AtividadeRepository extends CrudRepository<Atividade, Long> {

	List<Atividade> findAll();
}
