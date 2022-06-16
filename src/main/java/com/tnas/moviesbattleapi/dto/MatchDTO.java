package com.tnas.moviesbattleapi.dto;

public class MatchDTO {

	private String usuario;
	
	private Long quizId;
	
	private Long matchId;
	
	private String tituloFilme1;
	
	private String idFilme1;
	
	private String tituloFilme2;
	
	private String idFilme2;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public String getTituloFilme1() {
		return tituloFilme1;
	}

	public void setTituloFilme1(String tituloFilme1) {
		this.tituloFilme1 = tituloFilme1;
	}

	public String getIdFilme1() {
		return idFilme1;
	}

	public void setIdFilme1(String idFilme1) {
		this.idFilme1 = idFilme1;
	}

	public String getTituloFilme2() {
		return tituloFilme2;
	}

	public void setTituloFilme2(String tituloFilme2) {
		this.tituloFilme2 = tituloFilme2;
	}

	public String getIdFilme2() {
		return idFilme2;
	}

	public void setIdFilme2(String idFilme2) {
		this.idFilme2 = idFilme2;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	
}
