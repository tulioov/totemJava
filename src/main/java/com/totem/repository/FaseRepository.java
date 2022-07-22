package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Fase;

@Repository
public interface FaseRepository extends CrudRepository<Fase, Long> {

	List<Fase> findAll();
}
