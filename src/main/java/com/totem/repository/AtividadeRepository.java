package com.totem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.totem.entity.Atividade;

@Repository
public interface AtividadeRepository extends CrudRepository<Atividade, Long> {

	List<Atividade> findAll();
	
	List<Atividade> findByUsuarioDeleteIsNull();
	
	
	@Query(value = "select  " + 
			"	*  " + 
			"from  " + 
			"	atividade a  " + 
			"inner join local_atividade_list lal on  " + 
			"	lal.atividade_list_cod_atividade = a.cod_atividade   " + 
			"inner join especialidade_atividade_list eal on  " + 
			"	eal.atividade_list_cod_atividade = a.cod_atividade  " + 
			"inner join barco_template_atividade_list btal on  " + 
			"	btal.atividade_list_cod_atividade = a.cod_atividade  " + 
			"	where lal.local_cod_local = :localId  " + 
			"	and eal.especialidade_cod_especialidade = :especialidadeId  " + 
			"	and btal.barco_template_cod_barco_template = :barcotemplateId", nativeQuery = true)
	List<Atividade> listarAtividadeByLocalIdEspecIdBarcoTemplateId( @Param("localId") Long localId,  @Param("especialidadeId") Long especialidadeId,
			 @Param("barcotemplateId") Long barcotemplateId);

	
}


