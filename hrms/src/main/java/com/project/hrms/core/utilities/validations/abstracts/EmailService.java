package com.project.hrms.core.utilities.validations.abstracts;

import com.project.hrms.entities.concretes.Employer;

public interface EmailService {
	boolean emailCheck(String email);

	boolean emailCheck(Employer employer);

	boolean isEmailUsed(String email);

	boolean isEmailUsed(Employer employer);

	void sendEmail(String email);
}
