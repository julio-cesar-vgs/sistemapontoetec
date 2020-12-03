package com.sistema.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Formula;

@Entity
public class RegistroPontos implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -3103538777426676920L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDateTime dtiniciojornada;
	private LocalDateTime dtiniciopausa;
	private LocalDateTime dtterminopausa;
	private LocalDateTime dtterminojornada;

	@ManyToOne
	private CadastroPessoas pessoa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDtiniciojornada() {
		return dtiniciojornada;
	}

	public void setDtiniciojornada(LocalDateTime dtiniciojornada) {
		this.dtiniciojornada = dtiniciojornada;
	}

	public LocalDateTime getDtiniciopausa() {
		return dtiniciopausa;
	}

	public void setDtiniciopausa(LocalDateTime dtiniciopausa) {
		this.dtiniciopausa = dtiniciopausa;
	}

	public LocalDateTime getDtterminopausa() {
		return dtterminopausa;
	}

	public void setDtterminopausa(LocalDateTime dtterminopausa) {
		this.dtterminopausa = dtterminopausa;
	}

	public LocalDateTime getDtterminojornada() {
		return dtterminojornada;
	}

	public void setDtterminojornada(LocalDateTime dtterminojornada) {
		this.dtterminojornada = dtterminojornada;
	}

	public CadastroPessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(CadastroPessoas pessoa) {
		this.pessoa = pessoa;
	}

}
