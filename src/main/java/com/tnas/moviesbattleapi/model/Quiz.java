package com.tnas.moviesbattleapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_QUIZ")
public class Quiz {
	
	public static final Integer MAX_ERRORS_ALLOWED = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "USER_ID")
	private String usuario;

	@Column(name = "NUM_ERRORS")
	private Integer erros;
	
	@OneToMany(cascade = CascadeType.ALL,
	           orphanRemoval = true, mappedBy = "partida",
	           fetch = FetchType.EAGER)
	private List<Match> rodadas;
	
	public Boolean isActiveByErrors() {
		return this.erros <= MAX_ERRORS_ALLOWED;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getErros() {
		return erros;
	}

	public void setErros(Integer erros) {
		this.erros = erros;
	}

	public List<Match> getRodadas() {
		return rodadas;
	}

	public void setRodadas(List<Match> rodadas) {
		this.rodadas = rodadas;
	}

}
