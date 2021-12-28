package br.com.zup.desafio.comics.marvelApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatorsResponse {;
	
	private List<CreatoresItems> items;
	
	public CreatorsResponse(@JsonProperty("items") List<CreatoresItems> items) {
		this.items = items;
	}
	
	public List<CreatoresItems> getItems() {
		return items;
	}
	
}
