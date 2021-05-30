package com.project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hrms.business.abstracts.EmployerService;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.ErrorResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.core.utilities.results.SuccessDataResult;
import com.project.hrms.core.utilities.results.SuccessResult;
import com.project.hrms.core.utilities.validations.abstracts.EmailService;
import com.project.hrms.core.utilities.validations.abstracts.ValidationService;
import com.project.hrms.dataAccess.abstracts.EmployerDao;
import com.project.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private ValidationService<Employer> validationService;
	private EmailService emailService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, ValidationService<Employer> validationService,
			EmailService emailService) {
		this.employerDao = employerDao;
		this.validationService = validationService;
		this.emailService = emailService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Is verenler listelendi.");
	}

	@Override
	public Result add(Employer employer) {
		if (!(this.validationService.validation(employer))) {
			return new ErrorResult("Lutfen bos alan birakmayin");
		} else if (this.emailService.emailCheck(employer)) {
			return new ErrorResult("Lutfen web sitenizine ait bir mail adresi giriniz");
		} else if (!(this.emailService.isEmailUsed(employer))) {
			return new ErrorResult("Bu email adresi zaten kullanilmis");
		} else {
			this.employerDao.save(employer);
			this.emailService.sendEmail(employer.getEmail());
			return new SuccessResult(
					"Is veren eklendi. Aktivasyon maili gonderildi. Kaydin tamamlanabilmesi icin personel onayı beklenmektedir.");
		}
	}
}
