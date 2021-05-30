package com.project.hrms.core.utilities.validations.concretes;

import org.springframework.stereotype.Service;

import com.project.hrms.core.utilities.validations.abstracts.ValidationService;

@Service
public class ValidationManager<T> implements ValidationService<T> {

	@Override
	public boolean validation(T entity) {
		if (entity.equals(null))
			return false;
		return true;
	}
}