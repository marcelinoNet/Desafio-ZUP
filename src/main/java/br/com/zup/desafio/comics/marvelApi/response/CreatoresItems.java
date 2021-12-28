package br.com.zup.desafio.comics.marvelApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatoresItems {
	
	private String name;
	
	
	public CreatoresItems(@JsonProperty("name") String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
