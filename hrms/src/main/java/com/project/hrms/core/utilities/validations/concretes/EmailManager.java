package com.project.hrms.core.utilities.validations.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hrms.core.utilities.validations.abstracts.EmailService;
import com.project.hrms.dataAccess.abstracts.EmployerDao;
import com.project.hrms.dataAccess.abstracts.JobSeekerDao;
import com.project.hrms.entities.concretes.Employer;
import com.project.hrms.entities.concretes.JobSeeker;

@Service
public class EmailManager implements EmailService {
	private JobSeekerDao jobSeekerDao;
	private EmployerDao employerDao;

	@Autowired
	public EmailManager(JobSeekerDao jobSeekerDao, EmployerDao employerDao) {
		this.jobSeekerDao = jobSeekerDao;
		this.employerDao = employerDao;
	}

	@Override
	public boolean emailCheck(String email) {
		String regex = "^[\\w]+(?:\\.[\\w]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isEmailUsed(String email) {
		for (JobSeeker jobSeeker : this.jobSeekerDao.findAll())
			if (jobSeeker.getEmail().equals(email))
				return false;
		return true;
	}

	@Override
	public boolean emailCheck(Employer employer) {
		String regex = "^[\\w]+(?:\\.[\\w]+)*@(?:[" + employer.getWebSite().substring(4) + "-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(employer.getEmail());
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmailUsed(Employer employer) {
		for (Employer employer1 : this.employerDao.findAll())
			if (employer1.getEmail().equals(employer.getEmail()))
				return false;
		return true;
	}

	@Override
	public void sendEmail(String email) {
		return;
	}
}
