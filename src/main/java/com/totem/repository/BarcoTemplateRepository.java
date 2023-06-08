package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.BarcoTemplate;

@Repository
public interface BarcoTemplateRepository extends CrudRepository<BarcoTemplate, Long> {

	List<BarcoTemplate> findAll();
}
