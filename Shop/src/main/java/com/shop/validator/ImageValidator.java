package com.shop.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("imageValidator")
public class ImageValidator implements Validator{
	
	public void validate(Object... objects) throws Exception {
		
		MultipartFile multipartFile = (MultipartFile) objects[0];
		
		if(multipartFile.isEmpty()) {
			throw new ValidationException(ValidationMessages.CHOOSE_IMAGE);
		}
	}

}
