package com.project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hrms.business.abstracts.SystemPersonnelService;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.ErrorResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.core.utilities.results.SuccessDataResult;
import com.project.hrms.core.utilities.results.SuccessResult;
import com.project.hrms.core.utilities.validations.abstracts.EmailService;
import com.project.hrms.core.utilities.validations.abstracts.ValidationService;
import com.project.hrms.dataAccess.abstracts.SystemPersonnelDao;
import com.project.hrms.entities.concretes.Employer;
import com.project.hrms.entities.concretes.SystemPersonnel;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {

	private SystemPersonnelDao systemPersonnelDao;
	private ValidationService<SystemPersonnel> validationService;
	private EmailService emailService;

	@Autowired
	public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao,
			ValidationService<SystemPersonnel> validationService, EmailService emailService) {
		this.systemPersonnelDao = systemPersonnelDao;
		this.validationService = validationService;
		this.emailService = emailService;
	}

	@Override
	public DataResult<List<SystemPersonnel>> getAll() {
		return new SuccessDataResult<List<SystemPersonnel>>(this.systemPersonnelDao.findAll(),
				"Sistem personelleri listelendi.");
	}

	@Override
	public Result add(SystemPersonnel systemPersonnel) {
		if (!(this.validationService.validation(systemPersonnel))) {
			return new ErrorResult("Lutfen bos alan birakmayin.");
		} else if (!(this.emailService.emailCheck(systemPersonnel.getEmail()))) {
			return new ErrorResult("Lutfen gecerli bir email adresi giriniz.");
		} else if (!(this.emailService.isEmailUsed(systemPersonnel.getEmail()))) {
			return new ErrorResult("Bu email adresi zaten kullanilmis.");
		} else {
			this.systemPersonnelDao.save(systemPersonnel);
			return new SuccessResult("Sistem personeli eklendi.");
		}

	}

	@Override
	public Result staffApproval(Employer employer) {
		employer.setPersonnelVerification(true);
		return new SuccessResult("Is veren onaylandi.");

	}

}
