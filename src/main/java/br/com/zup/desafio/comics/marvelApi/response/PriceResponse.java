package br.com.zup.desafio.comics.marvelApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceResponse {
	
	private Double price;
	
	public PriceResponse(@JsonProperty("price") Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "" + price;
	}
	
	

}
