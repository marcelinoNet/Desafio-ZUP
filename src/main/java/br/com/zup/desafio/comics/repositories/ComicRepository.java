package br.com.zup.desafio.comics.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.desafio.comics.entities.Comic;
import br.com.zup.desafio.comics.entities.User;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{
	
	List<Comic> findComicsByUser(User user);
}
