package com.sistema.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.models.CadastroPessoas;
import com.sistema.models.RegistroPontos;

public interface RegistroPontosRepository extends CrudRepository<RegistroPontos, String>{
	
	RegistroPontos findById(int id);
	
	@Query(value = "Select * from registro_pontos where pessoa_codigo=:idRegistro order by id desc", nativeQuery=true)
	Iterable<RegistroPontos> findByPessoa(@Param("idRegistro") Integer idPessoa);
	
	@Query(value = "Select if(max(id) is null,0,max(id)) from registro_pontos where pessoa_Codigo=:idPessoa", nativeQuery=true)
	int ConsultaUltimoRegistro(@Param("idPessoa") Integer idPessoa);
	
/*	@Transactional
	@Modifying
	@Query(value = "Update registro_pontos set"
			+ "dtiniciopausa=:iniciopausa"
			+ "where id=:idregistro", nativeQuery=true)
	void atualizaPonto(	@Param("dtiniciopausa") LocalDateTime inicioPausa,
											@Param("idregistro") Integer idRegistro);*/
	
/*	@Query(value = "Select * from registro_pontos order by id desc", nativeQuery=true)
	Iterable<RegistroPontos> findByPonto(@Param("id") int id);*/
}
