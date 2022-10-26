/*
 * Subash rajinikanth M
 *
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * This is the simple Recruitment System console application,
 * add the employee and candidate details to the mysql Database
 * using JDBC
 * 
 */
package com.i2i.recruitment.model;

import java.util.Date;
import java.util.List;

import com.i2i.recruitment.model.Employee;

/**
 * POJO Class of Candidate - All deteails are private, if we want access the data members can use geter and setters
 *
 * @version 1.0
 * @author Subashrajinikanth M
 */
public class Candidate {  
    private String name;
    private int id;
    private String age;
    private String mobile;
    private String email;
    private int yearOfPassedOut;
    private float cgpa;
    private String qualification;
    private String skill;
    private Date dateOfBirth;
    private float experience;
    private double salary;
    private String address;
    private List<Employee> employees;

    /**
     * we can get and display the details using getter
     * 
     */
    public List<Employee> getCandidateEmployees() {
	return employees;
    }

    public String getCandidateName() {
	return name;
    }

    public int getCandidateId() {
	return id;
    }

    public String getCandidateAge() {
	return age;
    }

    public String getCandidateMobile() {
	return mobile;
    }

    public String getCandidateEmail() {
	return email;
    }

    public int getCandidateYearOfPassedOut() {
	return yearOfPassedOut;
    }

    public float getCandidateCgpa() {
	return cgpa;
    }
    
    public String getCandidateQualification() {
	return qualification;
    }

    public String getCandidateSkill() {
	return skill;
    }

    public Date getCandidateDateOfBirth() {
	return dateOfBirth;
    }

    public float getCandidateExperience() {
	return experience;
    }

    public double getCandidateSalary() {
	return salary;
    }

    public String getCandidateAddress() {
	return address;
    }

    /**
     * we can get and update the details using setter
     *
     */
    public void setCandidateName(String name) {
	this.name = name;
    }

    public void setCandidateId(int id) {
	this.id = id;
    }

    public void setCandidateAge(String age) {
	this.age = age;
    }
    public void setCandidateMobile(String mobile) {
	this.mobile = mobile;
    }

    public void setCandidateEmail(String email) {
	this.email = email;
    }

    public void setCandidateYearOfPassedOut(int yearOfPassedOut) {
	this.yearOfPassedOut = yearOfPassedOut;
    }

    public void setCandidateCgpa(float cgpa) {
	this.cgpa = cgpa;
    }

    public void setCandidateQualification(String qualification) {
	this.qualification = qualification;
    }

    public void setCandidateSkill(String skill) {
	this.skill = skill;
    }

    public void setCandidateDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public void setCandidateExperience(float experience) {
	this.experience = experience;
    }

    public void setCandidateSalary(double salary) {
	this.salary = salary;
    }

    public void setCandidateAddress(String address) {
	this.address = address;
    }

    public void setCandidateEmployees(List<Employee> employees) {
	this.employees = employees;
    }
    
    @Override
    public String toString() {
        StringBuilder candidateProfile = new StringBuilder();

        candidateProfile.append("Candidate Name              : ").append(getCandidateName())
                        .append("\nCandidate Id              : ").append(getCandidateId())
                        .append("\nCandidate Mobile          : ").append(getCandidateMobile())
                        .append("\nCandidate Email           : ").append(getCandidateEmail())
                        .append("\nCandidate DateOfBirth     : ").append(getCandidateDateOfBirth())
                        .append("\nCandidate YeraOfPassedOut : ").append(getCandidateYearOfPassedOut())
                        .append("\nCandidate Degree          : " ).append(getCandidateQualification())
                        .append("\nCandidate Skill           : ").append(getCandidateSkill())
                        .append("\nCandidate Experience      : ").append(getCandidateExperience())
                        .append("\nCandidate Salary          : ").append(getCandidateSalary())
                        .append("\nCandidate Address         : ").append(getCandidateAddress());
        return candidateProfile.toString();
    } 
}