package br.com.zup.desafio.comics.marvelApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultsResponse {
	
	@JsonProperty("id")
	private Integer comicId;
	@JsonProperty("title")
	private String title;
	@JsonProperty("isbn")
	private String isbn;
	@JsonProperty("description")
	private String description;
	@JsonProperty("prices")
	private List<PriceResponse> prices;
	@JsonProperty("creators")
	private CreatorsResponse creators;
	
	
	public ResultsResponse(Integer comicId, String title, String isbn, 
			String description, List<PriceResponse> prices, 
			CreatorsResponse creators) {
		this.comicId = comicId;
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.prices = prices;
		this.creators = creators;
	}


	public Integer getComicId() {
		return comicId;
	}


	public String getTitle() {
		return title;
	}


	public String getIsbn() {
		return isbn;
	}


	public String getDescription() {
		return description;
	}


	public Double getPrices() {
		return prices.get(0).getPrice();
	}


	public CreatorsResponse getCreators() {
		return creators;
	}
	
}
