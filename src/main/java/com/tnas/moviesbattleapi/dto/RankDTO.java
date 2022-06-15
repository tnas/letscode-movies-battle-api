package com.tnas.moviesbattleapi.dto;

public class RankDTO {

	private String usuario;
	
	private Double pontuacao;

	public RankDTO(String usuario, Double pontuacao) {
		super();
		this.usuario = usuario;
		this.pontuacao = pontuacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}
	

}
