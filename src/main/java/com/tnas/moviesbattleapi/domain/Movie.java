package com.tnas.moviesbattleapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

	@JsonProperty("imdbID")
	private String id;
	
	@JsonProperty("Year")
	private Integer ano;
	
	@JsonProperty("Title")
	private String titulo;
	
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
