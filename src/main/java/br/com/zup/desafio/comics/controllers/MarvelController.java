package br.com.zup.desafio.comics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.comics.marvelApi.client.MarvelComicsService;
import br.com.zup.desafio.comics.marvelApi.response.ComicsResponse;
import br.com.zup.desafio.comics.marvelApi.response.ResultsResponse;

@RestController
@RequestMapping(value = "/marvel")
public class MarvelController {
	@Autowired
	private MarvelComicsService service;
	
	@GetMapping("/comics")
	public ComicsResponse findAll() {
		return service.findAll();
	}
	
	@GetMapping	("/{id}")
	public ResultsResponse findById(@PathVariable Integer id){
		ResultsResponse response = service.findComicsById(id);
		return response;
		
	}
	

}
