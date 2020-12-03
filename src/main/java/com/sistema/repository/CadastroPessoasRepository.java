package com.sistema.repository;

import org.springframework.data.repository.CrudRepository;

import com.sistema.models.CadastroPessoas;

public interface CadastroPessoasRepository extends CrudRepository<CadastroPessoas, String> {
	CadastroPessoas findByCodigo (int codigo);
} 
