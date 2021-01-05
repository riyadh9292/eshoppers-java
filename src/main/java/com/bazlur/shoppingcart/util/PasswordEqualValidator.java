package com.bazlur.shoppingcart.util;

import java.util.Objects;
import com.bazlur.shoppingcart.util.PasswordEqual;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;



public class PasswordEqualValidator implements ConstraintValidator<PasswordEqual,Object>{
	private String firstFieldName;
	private String secondFieldName;
	private String message;
	public void initialize(PasswordEqual constraint) {
		firstFieldName=constraint.first();
		secondFieldName=constraint.second();
		message=constraint.message();
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		boolean valid=true;
		try {
			final Object firstObj=getValue(value,firstFieldName);
			final Object secondObj=getValue(value,secondFieldName);
			valid=Objects.equals(firstObj,secondObj);
		}catch(final Exception ignore) {
			//ignore
		}
		if(!valid) {
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(firstFieldName).addConstraintViolation().disableDefaultConstraintViolation();
		}
		return valid;
	}

	private Object getValue(Object object, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		Field field=object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

}
