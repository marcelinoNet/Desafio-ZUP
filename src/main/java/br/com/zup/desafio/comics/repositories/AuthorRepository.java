package br.com.zup.desafio.comics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.desafio.comics.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

}
