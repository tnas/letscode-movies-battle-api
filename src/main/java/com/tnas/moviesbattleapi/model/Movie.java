package com.tnas.moviesbattleapi.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

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

	private static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault()); 
	
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
	@JsonProperty("imdbRating")
	private String nota;
	
	@Column(name = "VOTOS")
	@JsonProperty("imdbVotes")
	private String votos;
	
	@Column(name = "PONTUACAO")
	private Double pontuacao;
	
	public void calcularPontuacao() {
		
		try {
			if (Objects.nonNull(this.nota) && Objects.nonNull(this.votos)) {
				this.pontuacao = numberFormat.parse(this.nota).doubleValue()  * 
					numberFormat.parse(this.votos).doubleValue();
			}
			else {
				this.pontuacao = 0d;
			}
		}
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getVotos() {
		return votos;
	}

	public void setVotos(String votos) {
		this.votos = votos;
	}

	public Double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", ano=" + ano + ", titulo=" + titulo + ", nota=" + nota + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
