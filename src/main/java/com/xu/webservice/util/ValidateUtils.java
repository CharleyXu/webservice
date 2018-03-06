package com.xu.webservice.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author charlie Created on 2018/3/6.
 */
@Component
public class ValidateUtils {
	private static final Logger logger = LoggerFactory.getLogger(ValidateUtils.class);

	public boolean validate(Object object){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> validate = validator.validate(object);
		int size = validate.size();
		if (size == 0){
			return true;
		}
		logger.warn(String.format("参数校验失败:%s",validate.iterator().next().getMessage()));
		return false;
	}
}
