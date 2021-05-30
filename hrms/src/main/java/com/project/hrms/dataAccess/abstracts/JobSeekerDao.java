package com.project.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

}
