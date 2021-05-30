package com.project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hrms.business.abstracts.JobPositionService;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.ErrorResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.core.utilities.results.SuccessDataResult;
import com.project.hrms.core.utilities.results.SuccessResult;
import com.project.hrms.dataAccess.abstracts.JobPositionDao;
import com.project.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "Is pozisyonlari listelendi.");
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if (this.positionUsed(jobPosition.getJobName())) {
			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("Pozisyon eklendi.");
		}
		return new ErrorResult("Bu pozisyon zaten var.");
	}

	private boolean positionUsed(String position) {
		for (JobPosition jobPosition : this.jobPositionDao.findAll()) {
			if (jobPosition.getJobName().equals(position))
				return false;
		}
		return true;
	}

}
