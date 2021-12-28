package br.com.zup.desafio.comics.marvelApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataResponse {

	@JsonProperty("results")
	private List<ResultsResponse> results;

	public DataResponse(@JsonProperty("results") List<ResultsResponse> results) {
		this.results = results;
	}
	
	@Deprecated
	public DataResponse() {}
	
	public ResultsResponse getResult() {
		return results.get(0);
	}
	
}
