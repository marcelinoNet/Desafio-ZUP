package br.com.zup.desafio.comics.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.desafio.comics.dtos.UserDTO;
import br.com.zup.desafio.comics.dtos.UserInsertDTO;
import br.com.zup.desafio.comics.dtos.UserUpdateDTO;
import br.com.zup.desafio.comics.entities.User;
import br.com.zup.desafio.comics.repositories.UserRepository;
import br.com.zup.desafio.comics.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<UserDTO> findAll(){
		List<User> list = userRepository.findAll();
		return list.stream().map((x) -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		convertDtoToEntity(dto, entity);
		entity = userRepository.save(entity);
		return new UserDTO(entity);
	}
	
	
	private void convertDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName().trim());
		entity.setEmail(dto.getEmail().toLowerCase().trim());
		entity.setCpf(dto.getCpf());
		entity.setBirthday(dto.getBirthday());
	}
	
	
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id){
		Optional<User> obj = userRepository.findById(id);
		User entity = obj.orElseThrow(()-> 
			new ResourceNotFoundException("Usuário não encontrado: {id = "+ id+"}"));
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO update(Long id,UserUpdateDTO dto) {
		try {
			User entity = userRepository.getOne(id);
			convertDtoToEntity(dto, entity);
			entity = userRepository.save(entity);
			return new UserDTO(entity);
		}catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Usuário não encontrado: {id = "+ id+"}");
		}
	}
	
	public void delete(Long id) {
		try {
			userRepository.deleteById(id);	
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Usuário não encontrado: {id = "+ id+"}");
		}
	}
	
	
}
