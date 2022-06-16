package com.tnas.moviesbattleapi.dto;

import com.tnas.moviesbattleapi.model.Solution;

public class SolutionMatchDTO {

	private Long matchId;
	
	private Solution resposta;

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Solution getResposta() {
		return resposta;
	}

	public void setResposta(Solution resposta) {
		this.resposta = resposta;
	}
	
}
