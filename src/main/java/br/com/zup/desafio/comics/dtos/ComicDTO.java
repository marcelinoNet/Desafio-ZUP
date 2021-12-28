package br.com.zup.desafio.comics.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zup.desafio.comics.entities.Comic;

public class ComicDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	
	@NotNull(message = "ComicId é obrigatório")
	private Integer comicId;
	@NotBlank(message = "O Título é obrigatório")
	private String title;
	@PositiveOrZero
	@NotNull(message = "O Preço é obrigatório")
	private Double price;
	@NotBlank(message = "O ISBN é obrigatório")
	private String isbn;
	private String description;
	
	private boolean discount;
	
	List<AuthorDTO> authors = new ArrayList<>();
	
	public ComicDTO() {
	}

	public ComicDTO(Long id,Integer comicId, String title, Double price, String isbn, 
			String description, boolean discount) {
		this.id = id;
		this.comicId = comicId;
		this.title = title;
		this.price = price;
		this.isbn = isbn;
		this.description = description;
		this.discount = discount;
	}
	
	public ComicDTO(Comic entity) {
		id = entity.getId();
		comicId = entity.getComicId();
		title = entity.getTitle();
		price = entity.getPrice();
		isbn = entity.getIsbn();
		description = entity.getDescription();
		discount = entity.isDiscount();
		
		authors = entity.getAuthors().stream()
				.map(x-> new AuthorDTO(x)).collect(Collectors.toList());
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getComicId() {
		return comicId;
	}

	public void setComicId(Integer comicId) {
		this.comicId = comicId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public List<AuthorDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}
}
