package com.project.hrms.core.utilities.validations.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hrms.core.utilities.validations.abstracts.IdentificationService;
import com.project.hrms.dataAccess.abstracts.JobSeekerDao;
import com.project.hrms.entities.concretes.JobSeeker;

@Service
public class IdentificationManager implements IdentificationService {
	private JobSeekerDao jobSeekerDao;

	@Autowired
	public IdentificationManager(JobSeekerDao jobSeekerDao) {
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public boolean identificationCheck(String identificationNumber) {
		for (JobSeeker jobSeeker : this.jobSeekerDao.findAll())
			if (jobSeeker.getIdentificationNumber().equals(identificationNumber))
				return false;
		return true;
	}

}
