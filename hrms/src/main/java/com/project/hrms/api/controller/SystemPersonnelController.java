package com.project.hrms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hrms.business.abstracts.SystemPersonnelService;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.entities.concretes.SystemPersonnel;

@RestController
@RequestMapping("/api/personnels")
public class SystemPersonnelController {

	private SystemPersonnelService systemPersonnelService;

	@Autowired
	public SystemPersonnelController(SystemPersonnelService systemPersonnelService) {
		this.systemPersonnelService = systemPersonnelService;
	}

	@GetMapping("/getall")
	public DataResult<List<SystemPersonnel>> getAll() {
		return this.systemPersonnelService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody SystemPersonnel systemPersonnel) {
		return this.systemPersonnelService.add(systemPersonnel);
	}
}
