package com.project.hrms.core.adapters.mernis;

import org.springframework.stereotype.Service;

import com.project.hrms.entities.concretes.JobSeeker;

@Service
public class MernisServiceAdapter implements MernisServiceCheck {

	@Override
	public boolean isRealPersonCheck(JobSeeker jobSeeker) {
		if (jobSeeker.getBirthday().equals(null) && jobSeeker.getIdentificationNumber().equals(null)
				&& jobSeeker.getName().equals(null) && jobSeeker.getSurName().equals(null))
			return false;
		return true;
	}
}
