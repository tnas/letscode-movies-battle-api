package com.tnas.moviesbattleapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "TB_MOVIE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

	@Id
	@Column(name = "ID")
	@JsonProperty("imdbID")
	private String id;
	
	@Column(name = "ANO")
	@JsonProperty("Year")
	private Integer ano;
	
	@Column(name = "TITULO")
	@JsonProperty("Title")
	private String titulo;
	
	@Column(name = "NOTA")
	private Double nota;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", ano=" + ano + ", titulo=" + titulo + ", nota=" + nota + "]";
	}
	
}
