package com.sistema.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Funcao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1719318668620836018L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String descricaofuncao;
	
	@OneToMany
	private List<CadastroPessoas> pessoa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricaofuncao() {
		return descricaofuncao;
	}

	public void setDescricaofuncao(String descricaofuncao) {
		this.descricaofuncao = descricaofuncao;
	}

	public List<CadastroPessoas> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<CadastroPessoas> pessoa) {
		this.pessoa = pessoa;
	}

	
}
