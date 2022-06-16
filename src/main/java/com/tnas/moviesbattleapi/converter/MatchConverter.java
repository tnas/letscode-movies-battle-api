package com.tnas.moviesbattleapi.converter;

import com.tnas.moviesbattleapi.dto.MatchDTO;
import com.tnas.moviesbattleapi.model.Match;

public class MatchConverter {

	public static MatchDTO getDto(Match match) {
		
		var matchDto = new MatchDTO();
		
		matchDto.setUsuario(match.getPartida().getUsuario());
		matchDto.setQuizId(match.getPartida().getId());
		matchDto.setMatchId(match.getId());
		matchDto.setIdFilme1(match.getPrimeiroFilme().getId());
		matchDto.setTituloFilme1(match.getPrimeiroFilme().getTitulo());
		matchDto.setIdFilme2(match.getSegundoFilme().getId());
		matchDto.setTituloFilme2(match.getSegundoFilme().getTitulo());
		
		return matchDto;
	}
}
