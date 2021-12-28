package br.com.zup.desafio.comics.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.desafio.comics.entities.User;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O Nome é obrigatório!!!")
	private String name;
	
	@NotBlank(message = "O E-mail é obrigatório!!!")
	@Email(message = "O e-mail deve ser um e-mail válido!!!")
	private String email;
	
	@NotBlank(message = "O CPF é obrigatório!!!")
	@CPF(message = "O CPF deve ser válido")
	private String cpf;
	
	@NotNull(message = "A Data de Nascimento é obrigatório!!!")
	private LocalDate birthday;
	
	
	public UserDTO() {}

	public UserDTO(Long id, String name, String email, String cpf, LocalDate birthday) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birthday = birthday;
	}
	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		cpf = entity.getCpf();
		birthday = entity.getBirthday();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
}
