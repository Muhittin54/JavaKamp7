package com.project.hrms.core.utilities.validations.abstracts;

public interface ValidationService<T> {
	boolean validation(T entity);
}
