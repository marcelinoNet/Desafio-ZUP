package br.com.zup.desafio.comics.entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_comics")
public class Comic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer comicId;
	private String title;
	private Double price;
	@Column(unique = true)
	private String isbn;
	@Column(columnDefinition = "TEXT")
	private String description;
	private boolean discount;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tb_comic_author", 
			   joinColumns = @JoinColumn(name = "comic_id"), 
			   inverseJoinColumns = @JoinColumn(name = "author_id"))
	Set<Author> authors = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Comic() {
	}

	public Comic(Integer comicId, String title, 
			Double price, 
			String isbn, 
			String description, 
			boolean discount,
			User user) {
		this.comicId = comicId;
		this.title = title;
		this.price = price;
		this.isbn = isbn;
		this.description = description;
		this.discount = discount;
		this.user = user;
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
		if (apllyDiscount(getIsbn())) {
			setDiscount(true);
			return price - (price * 0.1);
		}
		setDiscount(false);
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

	public User getUsers() {
		return user;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	private boolean apllyDiscount(String isbn) {
		LocalDate localDate = LocalDate.now();
		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
		int digito = Integer.valueOf(isbn.substring(isbn.length() - 1));

		if (dayOfWeek.name().toString().equals("MONDAY") 
					&& (digito == 0 || digito == 1)) {
			return true;
		} else if (dayOfWeek.name().toString().equals("TUESDAY") 
					&& (digito == 2 || digito == 3)) {
			return true;
		} else if (dayOfWeek.name().toString().equals("WEDNESDAY") 
					&& (digito == 4 || digito == 5)) {
			return true;
		} else if (dayOfWeek.name().toString().equals("THURSDAY") 
					&& (digito == 6 || digito == 7)) {
			return true;
		} else if (dayOfWeek.name().toString().equals("FRIDAY") 
					&& (digito == 8 || digito == 9)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comic other = (Comic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
