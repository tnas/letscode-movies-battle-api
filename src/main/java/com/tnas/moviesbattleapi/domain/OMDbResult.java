package com.tnas.moviesbattleapi.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OMDbResult {

	@JsonProperty("Search")
	private Movie[] movies;
	
	@JsonProperty("totalResults")
	private String total;

	public Movie[] getMovies() {
		return movies;
	}

	public void setMovies(Movie[] movies) {
		this.movies = movies;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "OMDbResult [movies=" + Arrays.toString(movies) + ", total=" + total + "]";
	}
	
}
