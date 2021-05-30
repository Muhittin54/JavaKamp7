package com.project.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class JobSeeker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "identification_number")
	private String identificationNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surName;
	@Column(name = "birthday")
	private Date birthday;
	@Column(name = "email")
	private String email;
	@Column(name = "email_verification")
	private boolean emailVerification = false;
	@Column(name = "password")
	private String password;
	@Column(name = "phone_number")
	private String phoneNumber;
}
