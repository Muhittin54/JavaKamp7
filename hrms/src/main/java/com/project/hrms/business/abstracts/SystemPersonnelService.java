package com.project.hrms.business.abstracts;

import java.util.List;

import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.entities.concretes.Employer;
import com.project.hrms.entities.concretes.SystemPersonnel;

public interface SystemPersonnelService {
	DataResult<List<SystemPersonnel>> getAll();

	Result add(SystemPersonnel systemPersonnel);

	Result staffApproval(Employer employer);
}
