package com.tnas.moviesbattleapi.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OMDbSearch {

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
		return "OMDbSearch [movies=" + Arrays.toString(movies) + ", total=" + total + "]";
	}
	
}
