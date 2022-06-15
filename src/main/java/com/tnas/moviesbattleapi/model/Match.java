package com.tnas.moviesbattleapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MATCH")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "QUIZ_ID")
	private Quiz partida;
	
	@ManyToOne
	@JoinColumn(name = "MOVIE_ST_ID")
	private Movie primeiroFilme;
	
	@ManyToOne
	@JoinColumn(name = "MOVIE_ND_ID")
	private Movie segundoFilme;
	
	@Column(name = "IS_HIT")
	private Boolean respostaCerta;
	
	public Boolean isEqualPairMovies(Movie st, Movie nd) {
		return (primeiroFilme.equals(st) && segundoFilme.equals(nd)) ||
				(primeiroFilme.equals(nd) && segundoFilme.equals(st));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Quiz getPartida() {
		return partida;
	}

	public void setPartida(Quiz partida) {
		this.partida = partida;
	}

	public Movie getPrimeiroFilme() {
		return primeiroFilme;
	}

	public void setPrimeiroFilme(Movie primeiroFilme) {
		this.primeiroFilme = primeiroFilme;
	}

	public Movie getSegundoFilme() {
		return segundoFilme;
	}

	public void setSegundoFilme(Movie segundoFilme) {
		this.segundoFilme = segundoFilme;
	}

	public Boolean getRespostaCerta() {
		return respostaCerta;
	}

	public void setRespostaCerta(Boolean respostaCerta) {
		this.respostaCerta = respostaCerta;
	}
}
