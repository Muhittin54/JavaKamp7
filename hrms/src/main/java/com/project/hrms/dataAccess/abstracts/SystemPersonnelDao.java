package com.project.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hrms.entities.concretes.SystemPersonnel;

public interface SystemPersonnelDao extends JpaRepository<SystemPersonnel, Integer>{

}
