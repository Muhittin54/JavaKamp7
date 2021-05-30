package com.project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hrms.business.abstracts.JobSeekerService;
import com.project.hrms.core.adapters.mernis.MernisServiceCheck;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.ErrorResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.core.utilities.results.SuccessDataResult;
import com.project.hrms.core.utilities.results.SuccessResult;
import com.project.hrms.core.utilities.validations.abstracts.EmailService;
import com.project.hrms.core.utilities.validations.abstracts.IdentificationService;
import com.project.hrms.core.utilities.validations.abstracts.ValidationService;
import com.project.hrms.dataAccess.abstracts.JobSeekerDao;
import com.project.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {
	private JobSeekerDao jobSeekerDao;
	private MernisServiceCheck mernisServiceCheck;
	private ValidationService<JobSeeker> validationService;
	private EmailService emailService;
	private IdentificationService identificationService;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, MernisServiceCheck mernisServiceCheck,
			ValidationService<JobSeeker> validationService, EmailService emailService,
			IdentificationService identificationService) {
		this.jobSeekerDao = jobSeekerDao;
		this.mernisServiceCheck = mernisServiceCheck;
		this.validationService = validationService;
		this.emailService = emailService;
		this.identificationService = identificationService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Tum is arayanlar listelendi");
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		if (!(this.mernisServiceCheck.isRealPersonCheck(jobSeeker))) {
			return new ErrorResult("Kisi dogrulanamadi");
		} else if (!(this.validationService.validation(jobSeeker))) {
			return new ErrorResult("Lutfen bos alan birakmayin");
		} else if (!(this.emailService.emailCheck(jobSeeker.getEmail()))) {
			return new ErrorResult("Lutfen gecerli bir email adresi giriniz.");
		} else if (!(this.emailService.isEmailUsed(jobSeeker.getEmail()))) {
			return new ErrorResult("Bu email adresi zaten kullanilmis");
		} else if (!(this.identificationService.identificationCheck(jobSeeker.getIdentificationNumber()))) {
			return new ErrorResult("Bu kimlik numarasi zaten kullanilmis");
		} else {
			this.jobSeekerDao.save(jobSeeker);
			this.emailService.sendEmail(jobSeeker.getEmail());
			return new SuccessResult(
					"Is arayan kaydedildi. Kaydin tamamlanabilmesi için eposta adresinize gönderilen maile tiklayin.");
		}
	}
}
