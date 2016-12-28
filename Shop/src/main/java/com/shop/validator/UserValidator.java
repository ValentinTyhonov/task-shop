package com.shop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.dao.UserDao;
import com.shop.entity.User;

@Component("userValidator")
public class UserValidator implements Validator {
	
	@Autowired
	private UserDao userDao;

	public void validate(Object... objects) throws Exception {
		User user = (User) objects[0];
		String confirmPass = (String) objects[1];
		
		if(user.getName().isEmpty()) {
			throw new ValidationException(ValidationMessages.EMPTY_NAME_FIELD);
		} else if(user.getSurname().isEmpty()) {
			throw new ValidationException(ValidationMessages.EMPTY_SURNAME_FIELD);
		} else if(user.getEmail().isEmpty()) {
			throw new ValidationException(ValidationMessages.EMPTY_EMAIL_FIELD);
		} else if(userDao.getByEmail(user.getEmail()) != null) {
			throw new ValidationException(ValidationMessages.EMAIL_ALREADY_EXIST);
		} else if(user.getPassword().isEmpty()) {
			throw new ValidationException(ValidationMessages.EMPTY_PASSWORD_FIELD);
		} else if(user.getPassword().length() < 8) {
			throw new ValidationException(ValidationMessages.PASSWORD_SHOULD_BE_NOT_SHORTER_THEN_8);			
		} else if(confirmPass.equals("")) {
			throw new ValidationException(ValidationMessages.REPEAT_PASSWORD);
		} else if(!user.getPassword().equals(confirmPass)) {
			throw new ValidationException(ValidationMessages.PASSWORDS_ARE_NOT_EQUAL);
		}
	}

}
