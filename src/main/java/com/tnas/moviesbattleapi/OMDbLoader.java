package com.tnas.moviesbattleapi;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tnas.moviesbattleapi.domain.OMDbResult;

@Component
public class OMDbLoader implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>Running after booting application!!!!!!!!");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		var url = "http://www.omdbapi.com/?s=\"and\"&page=1&type=movie&apikey=NONENONE";
		var restTemplate = new RestTemplate();
		var omdbResult = restTemplate.getForObject(url, OMDbResult.class);
		System.out.println(omdbResult.toString());
		Stream.of(omdbResult.getMovies()).forEach(m -> {
			System.out.println(m.toString());
		});
		
	}

}
