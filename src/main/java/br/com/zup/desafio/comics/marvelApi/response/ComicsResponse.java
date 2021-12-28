package br.com.zup.desafio.comics.marvelApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComicsResponse {

	@JsonProperty("data")
	private DataResponse data;

	public ComicsResponse(@JsonProperty("data") DataResponse data) {
		this.data = data;
	}
	
	public ResultsResponse getResult() {
		return data.getResult();
	}
}
