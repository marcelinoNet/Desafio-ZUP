package br.com.zup.desafio.comics.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.zup.desafio.comics.dtos.UserDTO;
import br.com.zup.desafio.comics.entities.User;
import br.com.zup.desafio.comics.exceptions.FieldMessage;
import br.com.zup.desafio.comics.repositories.UserRepository;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserDTO>{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String> )request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
				
		User user = userRepository.findByEmail(dto.getEmail());
		if(user != null) {
			if(userId != user.getId()) {
				list.add(new FieldMessage("email", "Email já existe"));
			}
		}
		User user2 = userRepository.findByCpf(dto.getCpf());
		if(user2 != null) {
			if(userId != user2.getId()) {
				list.add(new FieldMessage("cpf", "CPF já existe"));
			}
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	

}
