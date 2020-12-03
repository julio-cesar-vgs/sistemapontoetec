package com.sistema.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4736739942219713365L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String descricao;
	
	@OneToMany
	private List<CadastroPessoas> pessoa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<CadastroPessoas> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<CadastroPessoas> pessoa) {
		this.pessoa = pessoa;
	}
	
}
