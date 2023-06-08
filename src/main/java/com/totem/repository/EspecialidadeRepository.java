package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Especialidade;

@Repository
public interface EspecialidadeRepository extends CrudRepository<Especialidade, Long> {

	List<Especialidade> findAll();
}
