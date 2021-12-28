package br.com.zup.desafio.comics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.comics.dtos.ComicDTO;
import br.com.zup.desafio.comics.marvelApi.client.MarvelComicsService;
import br.com.zup.desafio.comics.marvelApi.response.ResultsResponse;
import br.com.zup.desafio.comics.services.ComicsService;

@RestController
@RequestMapping(value = "/comics")
public class ComicController {
	
	@Autowired
	private ComicsService comicService;
	
	@Autowired
	private MarvelComicsService marvelService;

	@GetMapping
	public ResponseEntity<List<ComicDTO>> findAll(){
		List<ComicDTO> list = comicService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<List<ComicDTO>> findByUser(@PathVariable Long id){
		List<ComicDTO> list = comicService.findByUser(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(value="/{id}/{userId}")
	public ResponseEntity<ComicDTO> insertComicWithUser(@PathVariable Integer id, @PathVariable Long userId) {
		ResultsResponse response = marvelService.findComicsById(id);
		ComicDTO dto = comicService.insertComic(userId,response);
		return ResponseEntity.ok().body(dto);
	}
	
}
