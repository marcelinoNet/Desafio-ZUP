package br.com.zup.desafio.comics.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.desafio.comics.dtos.ComicDTO;
import br.com.zup.desafio.comics.entities.Author;
import br.com.zup.desafio.comics.entities.Comic;
import br.com.zup.desafio.comics.entities.User;
import br.com.zup.desafio.comics.marvelApi.response.CreatoresItems;
import br.com.zup.desafio.comics.marvelApi.response.ResultsResponse;
import br.com.zup.desafio.comics.repositories.ComicRepository;
import br.com.zup.desafio.comics.repositories.UserRepository;
import br.com.zup.desafio.comics.services.exceptions.ResourceNotFoundException;

@Service
public class ComicsService {

	@Autowired
	private ComicRepository comicRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional(readOnly = true)
	public List<ComicDTO> findAll() {
		List<Comic> list = comicRepository.findAll();
		return list.stream().map((x) -> new ComicDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ComicDTO> findByUser(Long userId) {
		Optional<User> obj = userRepository.findById(userId);
		User user = obj.orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado: {id = "+ userId+"}"));
		List<Comic> list = comicRepository.findComicsByUser(user);
		return list.stream().map(x -> new ComicDTO(x)).collect(Collectors.toList());
	}
	
	public ComicDTO insertComic(Long userId, ResultsResponse response) {
		Comic entity = new Comic();
		
		Optional<User> obj = userRepository.findById(userId);
		User user = obj.orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado: {id = "+ userId+"}"));
		entity = convertResponseToEntity(user, response);
		for(CreatoresItems item: response.getCreators().getItems()) {
			entity.getAuthors().add(new Author(item.getName()));
		}
		entity =  comicRepository.save(entity);
		return new ComicDTO(entity);
	}

	private Comic convertResponseToEntity(User user,ResultsResponse response) {
		
		return new Comic(
				response.getComicId(),
				response.getTitle(),
				response.getPrices(),
				response.getIsbn(),
				response.getDescription(),
				false,
				user
				);
	}
	
	
}
