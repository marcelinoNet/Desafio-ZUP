package br.com.zup.desafio.comics.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.zup.desafio.comics.dtos.UserDTO;
import br.com.zup.desafio.comics.entities.User;
import br.com.zup.desafio.comics.exceptions.FieldMessage;
import br.com.zup.desafio.comics.repositories.UserRepository;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserDTO>{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
		
		
		List<FieldMessage> list = new ArrayList<>();
				
		User user = userRepository.findByEmail(dto.getEmail());
		if(user != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}
		User user2 = userRepository.findByCpf(dto.getCpf());
		if(user2 != null) {
			list.add(new FieldMessage("cpf", "CPF já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	

}
