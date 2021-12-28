package br.com.zup.desafio.comics.marvelApi.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatorsResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<CreatoresItems> items;
	
	public CreatorsResponse(@JsonProperty("items") List<CreatoresItems> items) {
		this.items = items;
	}
	
	public List<CreatoresItems> getItems() {
		return items;
	}
	
}
