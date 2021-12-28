package br.com.zup.desafio.comics.marvelApi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.desafio.comics.marvelApi.response.ComicsResponse;

@FeignClient(url = "${marvel.url}/comics", name = "marvel-url")
public interface MarvelComicsClient {

	@GetMapping("/{id}")
	public ResponseEntity<ComicsResponse> findComicsById(
			@PathVariable Integer id,
			@RequestParam(value = "ts") Long ts,
			@RequestParam(value="apikey") String apikey,
			@RequestParam(value="hash") String hash
			);
	
	
	
	@GetMapping
	public ComicsResponse findAll(
			@RequestParam(value = "ts") Long ts,
			@RequestParam(value = "apikey") String apikey,
			@RequestParam(value= "hash") String hash);
	
}
