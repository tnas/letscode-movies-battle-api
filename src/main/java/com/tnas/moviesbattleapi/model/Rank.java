package com.tnas.moviesbattleapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_RANK")
public class Rank {

	@Id
	private String username;
	
	private Double pontuacao;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}

}
